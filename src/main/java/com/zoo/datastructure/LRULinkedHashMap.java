package com.zoo.datastructure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU缓存的简单实现，基于最新最新访问。
 * @param <K> 元素键类型
 * @param <V> 键映射的值类型
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;

    /**
     * 构造LRU缓存
     *
     * @param cacheSize 最多缓存的元素个数
     */
    public LRULinkedHashMap(int cacheSize) {
        // true 表示让 LinkedHashMap 按照访问顺序来进行排序，最近访问的放在队列尾部，最老访问的放在队列头部。
        // 默认是false，表示内部链表元素按插入顺序来排序，最早插入的放在队列头部，最后插入的放在队列尾部。
        super(16, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    /**
     * 该方法在一个元素被插入后被调用，返回的boolean值用于判断是否删除队列头部的元素。
     * @param eldest 队列头部元素first
     * @return 表示是否删除该元素的boolean值
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
        return size() > CACHE_SIZE;
    }
}
