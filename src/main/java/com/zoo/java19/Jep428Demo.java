package com.zoo.java19;

import jdk.incubator.concurrent.StructuredTaskScope;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 结构化并发功能还处于孵化阶段，该功能旨在简化多线程编程。结构化并发提供的特性将在不同线程中运行的多个任务视为一个工作单元，以简化错误处理和取消，提高了可靠性和可观测性
 */
public class Jep428Demo {
    record User(String name, Long id){}
    record Order(String orderNo, Long id){}
    record Response(User user, Order order){}
    private User findUser(){
        return new User("Java", 19L);
    }
    private Order fetchOrder(){
        // return new Order("20221001", 1L);
        throw new UnsupportedOperationException("fetchOrder");
    }
    private Response handle() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<User> user = scope.fork(() -> findUser());
            Future<Order> order = scope.fork(() -> fetchOrder());
            scope.join();
            scope.throwIfFailed();  // 如果任意一个子任务失败，抛出异常
            // 到这里时, 两个fork都执行成功了, 结果组合
            return new Response(user.resultNow(), order.resultNow());
        }
    }

    public static void main(String[] args) throws Exception {
        Jep428Demo demo = new Jep428Demo();
        demo.handle();
    }
}
