package jp.futasoft.vthread.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class Sample {
    // heavy task to be offloaded to another thread
    public static int compute(int n) {
        try {
            // Sleep for a specified amount of time
            // For example, 2000 milliseconds (2 seconds)
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            // Handle the interruption exception
            e.printStackTrace();
        }

        return n * 2;
    }

    // Fires off the async task immediately and uses the common fork-join pool by default
    // (unless you provide a custom executor). It's eager execution.
    public static CompletableFuture<Integer> create(int n) {
        // do not implement the long-running task here. still the main thread.
        return CompletableFuture.supplyAsync(() -> {
            // here, implement the long-running task to be offloaded to another thread
            return compute(n);
        });
    }

    //
    public static void main(String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1000");

        // create 10 threads with a loop
        for (int i = 0; i < 1000; i++) {
            // fire and forget
            create(4)
                    .thenApply(data -> {
                        System.out.println(Thread.currentThread());
                        return data + 1;
                    })
                    .thenAccept(System.out::println)
                    .thenRun(() -> System.out.println("Done!"));
        }

        // Get the common ForkJoinPool and print the pool size
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println("Common pool size: " + commonPool.getPoolSize());

        try {
            Thread.sleep(30_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Done!!!");
    }
}
