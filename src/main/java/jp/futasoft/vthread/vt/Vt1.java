package jp.futasoft.vthread.vt;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import static java.lang.Thread.sleep;


public class Vt1 {
    private static final Logger log = LoggerFactory.getLogger(Vt1.class);

    @SneakyThrows
    private static void sleep(Duration duration) {
        Thread.sleep(duration);  // unmount
        // calculate fibonacci
//        fibonacciRecursive(47);
        log.info("!!! sleep {} ms", duration.toMillis());
    }

    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    @SneakyThrows
    static void runMyTasks() {

        var factory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {

            List<Future<Integer>> futures = new ArrayList<>();

            for (int i = 0; i < 20; i++) {
                int finalI = i;
                Future<Integer> future = executor.submit(() -> {
                    sleep(Duration.ofSeconds(5));
                    log.info("!!! done task #{}", finalI);
                    return finalI;
                });
                futures.add(future);
            }

            log.info("!!! submitted all tasks");

            // Wait for all tasks to complete
            for (var future : futures) {
                log.info(String.valueOf(future.get()));
            }
        }
    }

    //
    public static void main(String[] args) {
        runMyTasks();
    }
}
