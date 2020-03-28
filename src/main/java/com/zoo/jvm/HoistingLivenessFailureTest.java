package com.zoo.jvm;

import java.util.concurrent.TimeUnit;

/**
 * '循环表达式外提'导致的'活性失败'<br>
 * 指定jvm的运行参数:-Djava.compiler=NONE可以避免JIT优化，但是其他优化都被去掉了。<br>
 * 因此需要对共享变量做同步访问，如volatile、加锁synchronized
 */
public class HoistingLivenessFailureTest {
    private static boolean flag = false;
    private static int i = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                flag = true;
                System.out.println("flag 被修改成 true");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while (!flag) {
            i++;
        }
        //如果开启JIT编译器，则上述while循环会被编译器编译成如下的形式，这种被称为'循环表达式外提'的优化，导致了"活性失败"。
        /*if(!flag){
            while (true) {
                i++;
            }
        }*/
        System.out.println("程序结束,i=" + i);
    }
}