package com.zoo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 可见性测试
 */
public class VisibilityTest {

    public static void main(String[] args) throws InterruptedException {
//        new LivenessFailure();
        new SynchronizedReadWrite();
    }

    /**
     * 活性失败：由于优化提升导致的活性失败，使得该程序永远不会停下来
     */
    private static class LivenessFailure{
        //这里可以用volatile关键字来优化，这样就不需要同步，也不会导致活性失败。
//        private static volatile boolean stopRequested;
        private static boolean stopRequested;

        private LivenessFailure() throws InterruptedException {
            Thread backgroundThread = new Thread(() -> {
                int i = 0;
                while (!stopRequested){
                    i++;
                }
                //没有同步，虚拟机会将上面的while循环优化成下面这样，被称作优化提升，结果是一个活性失败(这个程序并没有得到提升)
                /*if(!stopRequested){
                    while (true){
                        i++;
                    }
                }*/
            });
            backgroundThread.start();
            TimeUnit.SECONDS.sleep(1);
            stopRequested = true;
        }
    }


    /**
     * 只有读同步或写同步都是不行的，读写都同步才能起作用。
     */
    private static class SynchronizedReadWrite{
        private static boolean stopRequested;

        private static synchronized void requestStop(){
            stopRequested = true;
        }

        private static synchronized boolean stopRequested(){
            return stopRequested;
        }

        private SynchronizedReadWrite() throws InterruptedException {
            Thread backgroundThread = new Thread(() -> {
                int i = 0;
                while (!stopRequested()){
                    i++;
                }
            });
            backgroundThread.start();
            TimeUnit.SECONDS.sleep(1);
            requestStop();
        }
    }

    /**
     * volatile不能保证原子性。
     */
    private static class SafetyFailure{
        private static volatile int nextSerialNumber = 0 ;
        public static int generateSerialNumber(){
            //++并不是原子操作，在多线程调用该方法时，第一个线程和第二个线程可能会获取到相同的值，而不是每个线程获取到的值都是前一个线程+1的数，这就导致了安全失败。
            return nextSerialNumber++;
        }
    }

}
