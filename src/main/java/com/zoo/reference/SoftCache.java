package com.zoo.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftCache<T> {
    // 引用队列
    private ReferenceQueue<T> referenceQueue = new ReferenceQueue<>();
    // 保存软引用集合，在引用对象被回收后销毁
    private List<Reference<T>> list = new ArrayList<>();

    // 添加缓存对象
    public synchronized void add(T obj){
        // 构建软引用
        Reference<T> reference = new SoftReference<T>(obj, referenceQueue);
        // 加入列表中
        list.add(reference);
    }

    // 获取缓存对象
    public synchronized T get(int index){
        // 先对无效引用进行清理
        clear();
        if (index < 0 || list.size() < index){
            return null;
        }
        Reference<T> reference = list.get(index);
        return reference == null ? null : reference.get();
    }

    public int size(){
        return list.size();
    }

    @SuppressWarnings("unchecked")
    private void clear(){
        Reference<T> reference;
        while (null != (reference = (Reference<T>) referenceQueue.poll())){
            list.remove(reference);
        }
    }
}
