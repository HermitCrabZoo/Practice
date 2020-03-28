package com.zoo.concurrent;

import java.util.concurrent.*;

public class ExecutorCompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<String> service = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < 5; i++) {
            int seqNo = i;
            service.submit(() -> "HelloWorld-" + seqNo + "-" + Thread.currentThread().getName());
        }
        for (int j = 0; j < 5; j++) {
            System.out.println(service.take().get());
        }
        executor.shutdown();
    }
}
