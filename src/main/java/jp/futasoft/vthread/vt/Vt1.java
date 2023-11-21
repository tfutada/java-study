package jp.futasoft.vthread.vt;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;


public class Vt1 {
    private static final Logger log = LoggerFactory.getLogger(Vt1.class);

    @SneakyThrows
    private static void myTask(Duration duration) {
        log.info("『  {} ", Thread.currentThread());
        Thread.sleep(duration);  // unmount
//        fibonacciRecursive(47);
        log.info("』 {} ", Thread.currentThread());
    }

    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    @SneakyThrows
    static void runMyTasks() {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<Integer>> futures = new ArrayList<>();

            IntStream.range(0, 9).forEach(i -> {
                // Submit a task and get a Future
                Future<Integer> future = executor.submit(() -> {
                    myTask(Duration.ofSeconds(5));
                    log.info("done task #{}", i);
                    return i;
                });
                futures.add(future);
            });

            log.info("submitted all tasks");

            // Wait for all tasks to complete
            for (var future : futures) {
                log.info("result: {}", String.valueOf(future.get()));
            }
        }
    }

    //
    public static void main(String[] args) {
        runMyTasks();
    }
}
