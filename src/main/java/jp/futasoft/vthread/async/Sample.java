package jp.futasoft.vthread.async;

import java.util.concurrent.CompletableFuture;

public class Sample {
    // heavy task to be offloaded to another thread
    public static int compute(int n) {
        System.out.println(Thread.currentThread()); // fork-join pool thread
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
                    System.out.println(Thread.currentThread()); // main thread
                    return +1;
                })
                .thenAccept(System.out::println);

        job.join(); // This will block until the CompletableFuture is complete.

        System.out.println("Done!!!");
        System.out.println(Thread.currentThread());
    }
}
