package com.zoo.algorithm.sort;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)//每次测试一个实例，多线程共享同一个类实例
@Warmup(iterations = 100, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
public class BubbleSort {

    @State(Scope.Thread)//每个线程一个实例
    public static class BenchmarkParam {
        private final int[] array = {95, 85, 12, 52, 64, 74, 105, 502, 4, 7, 6, 1, 74, 60, 141, 19, 34, 45, 59};
    }

    public static void main(String[] args) throws RunnerException {
//        Clock clock = Clock.systemUTC();
//        BenchmarkParam param = new BenchmarkParam();
//        System.out.println(Arrs.join(",", param.array));
//        long start = clock.millis();
//        for (int i = 0; i < 10000000; i++) {
//            bubbleSort(param);
//            bubbleSort1(param);
//            bubbleSort2(param);
//            bubbleSort3(param);
//        }
//        long end = clock.millis();
//        System.out.println(Arrs.join(",", param.array));
//        System.out.println(end - start);

        Options opt = new OptionsBuilder()
                .include(BubbleSort.class.getSimpleName())
                .measurementIterations(1)
                .forks(1)
                .build();
        new Runner(opt).run();
    }


    /**
     * 冒泡排序
     *
     * @param param
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void bubbleSort(BenchmarkParam param) {
        int[] array = param.array;
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序-优化1：冒泡排序过程中，可以检测到整个序列是否已经排序完成，进而可以避免掉后续的循环
     *
     * @param param
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void bubbleSort1(BenchmarkParam param) {
        int[] array = param.array;
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            boolean isOrdered = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isOrdered = false;
                }
            }
            if (isOrdered) {
                break;//这一轮没有发生交换，说明已经排好序。
            }
        }
    }


    /**
     * 冒泡排序-优化2：进一步地，在每轮循环之后，可以确认，最后一次发生交换的位置之后的元素，都是已经排好序的，
     * 因此可以不再比较那个位置之后的元素，大幅度减少了比较的次数
     *
     * @param param
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void bubbleSort2(BenchmarkParam param) {
        int[] array = param.array;
        int temp = 0, n = array.length - 1, j1;
        for (int i = 0, len = n; i < len; i++) {
            int newN = 0;
            for (int j = 0; j < n; j++) {
                j1 = j + 1;
                if (array[j] > array[j1]) {
                    temp = array[j];
                    array[j] = array[j1];
                    array[j1] = temp;
                    newN = j1;
                }
            }
            n = newN;
            if (n == 0) {
                break;//位置没有变化说明已经交换好了,跳出循环
            }
        }
    }


    /**
     * 冒泡排序-优化3：进行双向的循环，正向循环把最大元素移动到末尾，逆向循环把最小元素移动到最前<br>
     * 该优化也被称为是鸡尾酒排序(CockTailSort)。
     *
     * @param param
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static void bubbleSort3(BenchmarkParam param) {
        int[] array = param.array;
        int temp = 0, j1, start = 0, end = array.length - 1;
        while (start <= end) {
            int nEnd = start;
            int nStart = end;
            for (int j = start; j < end; j++) {
                j1 = j + 1;
                if (array[j] > array[j1]) {
                    temp = array[j];
                    array[j] = array[j1];
                    array[j1] = temp;
                    nEnd = j1;
                }
            }
            end = nEnd - 1;//这里一个元素已经沉底了,所以下一次交换次数相比于最后一次交换要少1
            for (int j = end; j > start; j--) {
                j1 = j - 1;
                if (array[j] < array[j1]) {//后一个元素小于上一个元素,往上移动一次
                    temp = array[j];
                    array[j] = array[j1];
                    array[j1] = temp;
                    nStart = j1;
                }
            }
            start = nStart + 1;//这里一个元素到最上面了,所以下一次交换次数相比于上一次交换要少1
        }
    }


}
