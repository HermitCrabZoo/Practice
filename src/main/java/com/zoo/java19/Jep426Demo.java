package com.zoo.java19;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

/**
 * 向量API目前是第四次孵化，功能是表达向量计算，在运行时编译为CPU架构上的最佳向量指令，从而实现优于等效标量计算的性能。目前相关API都在jdk.incubator.vector包下
 */
public class Jep426Demo {

    static void scalarComputation(float[] a, float[] b, float[] c) {
        for (int i = 0; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
        }
    }
    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;
    private static void vectorComputation(float[] a, float[] b, float[] c) {
        for (int i = 0; i < a.length; i += SPECIES.length()) {
            var m = SPECIES.indexInRange(i, a.length);
            var va = FloatVector.fromArray(SPECIES, a, i, m);
            var vb = FloatVector.fromArray(SPECIES, b, i, m);
            var vc = va.mul(va).add(vb.mul(vb)).neg();
            vc.intoArray(c, i, m);
        }
    }
    public static void main(String[] args) {
        float[] tempA = {1.0f, 3.0f, 2.0f, 4.0f, 8.0f, 10.0f};
        float[] tempB = {1.0f, -1.0f, 5.0f, 3.0f, 8.0f, 9.0f};
        float[] tempC = {1.0f, 6.0f, 1.0f, 1.0f, 1.0f, 1.0f};

        int scaleUpFactor = 10000;
        float[] a = new float[tempA.length * scaleUpFactor];
        float[] b = new float[tempA.length * scaleUpFactor];
        float[] c = new float[tempA.length * scaleUpFactor];
        for (int i = 0; i < scaleUpFactor; i++) {
            for (int j = 0; j < tempA.length; j++) {
                int idx = i * tempA.length + j;
                a[idx] = tempA[j];
                b[idx] = tempB[j];
                c[idx] = tempC[j];
            }
        }

        long startScalar = System.nanoTime();
        scalarComputation(a, b, c);
        System.out.printf("scalar computation cost %d nanoseconds\n", System.nanoTime() - startScalar);
        long startVector = System.nanoTime();
        vectorComputation(a, b, c);
        System.out.printf("vector computation cost %d nanoseconds\n", System.nanoTime() - startVector);
    }
}