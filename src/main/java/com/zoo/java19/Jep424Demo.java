package com.zoo.java19;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SequenceLayout;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.MemoryLayout.PathElement;
import static java.lang.foreign.ValueLayout.JAVA_INT;

/**
 * 		引入一个 API，Java 程序可以通过该 API 与 Java 运行时之外的代码和数据进行互操作。通过该 API
 * 		可有效地调用外部函数（ JVM 之外的代码）和安全地访问外部内存（不受 JVM 管理的内存），使得 Java
 * 程序能够调用本机库并处理本机数据，而不会出现 JNI 的脆弱性和危险。
 */
public class Jep424Demo {

    public static void main(String[] args) {
        new Jep424Demo().memoryOperation();
    }

    public void memoryOperation() {
        /*
         * 1. 创建结构化的顺序内存布局，结构如下
         * struct Point {
         *     int x;
         *     int y;
         * } pts[10];
         */
        SequenceLayout ptsLayout = MemoryLayout.sequenceLayout(10, MemoryLayout.structLayout(
                JAVA_INT.withName("x"),
                JAVA_INT.withName("y")));

        // 2. 分配内存并对内存设置值
        VarHandle xHandle = ptsLayout.varHandle(PathElement.sequenceElement(), PathElement.groupElement("x"));
        VarHandle yHandle = ptsLayout.varHandle(PathElement.sequenceElement(), PathElement.groupElement("y"));
        MemorySegment segment = MemorySegment.allocateNative(ptsLayout, MemorySession.openImplicit());
        for (int i = 0; i < ptsLayout.elementCount(); i++) {
            xHandle.set(segment,/* index */ (long) i, /* value to write */i); // x
            yHandle.set(segment,/* index */ (long) i, /* value to write */i); // y
            System.out.printf("index => %d, x = %d, y = %d\n", i, i, i);
        }

        // 3. 获取内存值
        int xValue = (int) xHandle.get(segment, 3);
        System.out.println("Point[3].x = " + xValue);
        int yValue = (int) yHandle.get(segment, 6);
        System.out.println("Point[6].y = " + yValue);
    }
}