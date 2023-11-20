package jp.futasoft.vthread.async;

import java.util.concurrent.CompletableFuture;

public class Sample {
    // heavy task to be offloaded to another thread
    public static int compute(int n) {
        try {
            // Sleep for a specified amount of time
            // For example, 2000 milliseconds (2 seconds)
            Thread.sleep(3_000);
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

    public static void main(String[] args) {
        var job = create(4)
                .thenApply(data -> {
                    System.out.println(Thread.currentThread());
                    return data + 1;
                })
                .thenAccept(System.out::println)
                .thenAccept(data -> {
                    System.out.println("data: " + data);
                })
                .thenRun(() -> System.out.println("Done!"));

        job.join();
        try {
            // Sleep for a specified amount of time
            // For example, 2000 milliseconds (2 seconds)
            Thread.sleep(30_000);
        } catch (InterruptedException e) {
            // Handle the interruption exception
            e.printStackTrace();
        }

        System.out.println("Done!!!");
    }
}
