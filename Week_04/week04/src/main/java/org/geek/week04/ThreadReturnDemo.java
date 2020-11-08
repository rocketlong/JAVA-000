package org.geek.week04;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadReturnDemo {

    /**
     * 1、线程池 execute 和 submit 一个有返回值，一个没返回值
     * 2、execute 线程内的方法执行出错，线程内会直接抛出异常；submit 线程内则不不会抛出异常，除非调用 future.get() 方法，才会抛出
     * 3、下面演示的所有获取线程内的返回值，都是阻塞获取
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(5, 10,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(100),
            r -> new Thread(r, "echo-" + r.hashCode()),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        try {
            // 1.Callable
            Future<Integer> callableFutureResult = executorService.submit(() -> {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
                return new Random().nextInt();
            });
            System.out.println("Callable result: " + callableFutureResult.get());

            // 2.FutureTask 这种方式 execute 也能获取返回值
            FutureTask<Integer> futureTask = new FutureTask<>(() -> {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName());
                return new Random().nextInt();
            });
            executorService.execute(futureTask);
            System.out.println("FutureTask result: " + futureTask.get());

            // 3.CompletableFuture
            Integer completableFutureResult = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                return new Random().nextInt();
            }, executorService).thenApplyAsync(v -> {
                System.out.println(Thread.currentThread().getName());
                return v + 1;
            }).exceptionally(e -> {
                // 异常处理，由抛出异常的线程执行
                System.out.println(Thread.currentThread().getName());
                e.printStackTrace();
                return 0;
            }).join();
            System.out.println("completableFuture result :" + completableFutureResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}
