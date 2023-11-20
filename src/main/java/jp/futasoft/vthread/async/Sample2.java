package jp.futasoft.vthread.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class Sample2 {
    // heavy task to be offloaded to another thread
    public static String compute(int n) {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            // Handle the interruption exception
            e.printStackTrace();
        }

        // convert n to string


        return "task #" + n;
    }

    public static CompletableFuture<String> create(int n) {
        return CompletableFuture.supplyAsync(() -> {
            return compute(n);
        });
    }

    //
    public static void main(String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100");

        // a list to hold jobs
        List<CompletableFuture<Void>> jobs = new ArrayList<>();
        // create 10 threads with a loop
        for (int i = 0; i < 1000; i++) {
            // fire and forget
            var job = create(i)
                    .thenAccept(System.out::println)
                    .thenRun(() -> System.out.println("Done!"));
            jobs.add(job);
        }

        // Get the common ForkJoinPool and print the pool size
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println("Common pool size: " + commonPool.getPoolSize());

        // join the jobs
        CompletableFuture.allOf(jobs.toArray(new CompletableFuture[0])).join();

        System.out.println("Done!!!");
    }
}
