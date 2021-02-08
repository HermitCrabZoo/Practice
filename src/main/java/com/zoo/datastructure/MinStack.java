package com.zoo.datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MinStack {

    private final List<Integer> data = new ArrayList<>();
    /**
     * 辅助栈用于存储最小元素的索引
     */
    private final ArrayDeque<Integer> indices = new ArrayDeque<>();

    public void push(int num) throws Exception {
        Integer idx = indices.peekLast();
        if(idx==null || num < min()){
            // 当前值小于最小值则将索引存到队尾
            indices.push(data.size());
        }
        data.add(num);
    }

    public int pop() throws IllegalStateException {
        // 栈空，抛出异常
        if(data.isEmpty()) {
            throw new IllegalStateException("栈为空");
        }
        // pop时先获取索引
        int popIndex = data.size() - 1;
        // 获取索引栈顶元素，它是最小值索引
        int minIndex = indices.peekLast();
        // 如果pop出去的索引就是最小值索引，索引才出栈
        if(popIndex == minIndex) {
            indices.pop();
        }
        return data.remove(popIndex);
    }

    public int min() throws IllegalStateException{
        // 栈空，抛出异常
        if(data.isEmpty()) {
            throw new IllegalStateException("栈为空");
        }
        // 获取栈顶元素，它是最小值的索引
        return data.get(indices.peekLast());
    }

}
