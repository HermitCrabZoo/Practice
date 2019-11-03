package com.zoo.concurrent;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 过度同步容易导致死锁。
 * 为了避免活性失败和安全性失败，在一个被同步的方法或代码块中，永远不要放弃对客户端的控制，换句话说，在一个同步区内，不要调用被设计成要被覆盖的方法，或者是由客户端以函数对象的形式提供的方法。
 */
public class OverSynchronized {


    public static void main(String[] args) {
        //使用SynchronizedObservableSet的实现会产生以下①②问题
//        ObservableSet<Integer> set = new SynchronizedObservableSet<>(new HashSet<>());
        //使用SimplifyObservableSet的实现则不会产生以下①②问题
        ObservableSet<Integer> set = new SimplifyObservableSet<>(new HashSet<>());
        set.addObserver((s, e) -> System.out.println(e));
        //①这里添加一个观察者用来将自己从ObservableSet中删掉，而删掉的步骤是在迭代过程中，所以会快速失败而抛出java.util.ConcurrentModificationException异常
        //这里不会产生死锁是由于，同一个线程，而synchronized是可重入的。
        set.addObserver(new SetObserver<>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    set.removeObserver(this);
                }
            }
        });
        //②这里添加一个观察者用来在另一个线程中将自己从ObservableSet中删掉。这会导致死锁，主线程获取到锁等待线程池中的线程执行完，线程池中的线程又在等待主线程释放锁，所以双方条件都得不到满足。
        set.addObserver(new SetObserver<>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService exec = Executors.newSingleThreadExecutor();
                    try {
                        exec.submit(()->set.removeObserver(this)).get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }finally {
                        exec.shutdown();
                    }
                }
            }
        });
        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    /**
     * 观察者集合，每当一个元素添加进集合内，就通知所有观察者。
     * @param <E> 集合元素类型
     */
    private abstract static class ObservableSet<E> extends ForwardingSet<E>{
        final List<SetObserver<E>> observers;

        private ObservableSet(Set<E> s, List<SetObserver<E>> observers) {
            super(s);
            this.observers = observers;
        }

        public abstract void addObserver(SetObserver<E> observer);

        public abstract boolean removeObserver(SetObserver<E> observer);

        protected abstract void notifyElementAdded(E element);

        @Override
        public boolean add(E element) {
            boolean added = super.add(element);
            if (added) {
                notifyElementAdded(element);
            }
            return added;
        }
    }


    private static class  SynchronizedObservableSet<E> extends ObservableSet<E>{

        private SynchronizedObservableSet(Set<E> s) {
            super(s, new ArrayList<>());
        }

        @Override
        public boolean removeObserver(SetObserver<E> observer) {
            synchronized (observers) {
                return observers.remove(observer);
            }
        }

        @Override
        public void addObserver(SetObserver<E> observer) {
            synchronized (observers) {
                observers.add(observer);
            }
        }

        /**
         * 此实现会可能会导致死锁发生
         * @param element 添加到集合里的元素
         */
        protected void notifyElementAdded(E element) {
            //同步块里面调用外来方法(可以被覆盖的方法)
            synchronized (observers) {
                for (SetObserver<E> observer : observers) {
                    observer.added(this, element);
                }
            }
        }

        /**
         * 此实现可解决死锁的发生，但不够简洁
         * @param element 添加到集合里的元素
         */
        /*private void notifyElementAdded(E element) {
            List<SetObserver<E>> snapshot = null;
            synchronized (observers) {
                snapshot = new ArrayList<>(observers);
            }
            //将外来方法的调用移出同步块即可解决快速失败和死锁的问题。
            for (SetObserver<E> observer : observers) {
                observer.added(this, element);
            }
        }*/

    }


    /**
     * 内部使用CopyOnWriteArrayList可以用一种简洁的方式解决ObservableSet的快速失败和死锁问题。
     * @param <E> 集合元素类型
     */
    private static class SimplifyObservableSet<E> extends ObservableSet<E>{

        private SimplifyObservableSet(Set<E> s) {
            super(s, new CopyOnWriteArrayList<>());
        }
        public void addObserver(SetObserver<E> observer) {
                observers.add(observer);
        }

        public boolean removeObserver(SetObserver<E> observer) {
                return observers.remove(observer);
        }

        protected void notifyElementAdded(E element) {
            //同步块里面调用外来方法(可以被覆盖的方法)，这里的observers的实现是CopyOnWriteArrayList，所以可以解决异常和死锁问题。
            for (SetObserver<E> observer : observers) {
                observer.added(this, element);
            }
        }
    }


    /**
     * 集合观察者
     * @param <E> 集合元素类型
     */
    @FunctionalInterface
    private interface SetObserver<E> {
        //在一个元素(element)被添加到ObservableSet对象时调用此方法。
        void added(ObservableSet<E> set, E element);
    }


    /**
     * 复合（composition），将所有方法转发到Set的方法
     * @param <E> 集合元素类型
     */
    private static class ForwardingSet<E> implements Set<E>{

        private final Set<E> s;

        private ForwardingSet(Set<E> s) {
            this.s = s;
        }

        @Override
        public int size() {
            return s.size();
        }

        @Override
        public boolean isEmpty() {
            return s.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return s.contains(o);
        }

        @Override
        public Iterator<E> iterator() {
            return s.iterator();
        }

        @Override
        public Object[] toArray() {
            return s.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return s.toArray(a);
        }

        @Override
        public boolean add(E e) {
            return s.add(e);
        }

        @Override
        public boolean remove(Object o) {
            return s.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return s.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return s.addAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return s.retainAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return s.removeAll(c);
        }

        @Override
        public void clear() {
            s.clear();
        }

        @Override
        public boolean equals(Object obj) {
            return s.equals(obj);
        }

        @Override
        public int hashCode() {
            return s.hashCode();
        }

        @Override
        public String toString() {
            return s.toString();
        }
    }


}
