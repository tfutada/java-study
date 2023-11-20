package jp.futasoft.vthread.vt;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.lang.Thread.sleep;


public class Vt1 {
    private static final Logger log = LoggerFactory.getLogger(Vt1.class);

    @SneakyThrows
    private static void sleep(Duration duration) {
        Thread.sleep(duration);  // unmount
    }

    @SneakyThrows
    static void runMyTasks() {

        var factory = Thread.ofVirtual().name("routine-", 0).factory();

        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {

            var future = executor.submit(() -> {
                log.info("I'm going to take a bath");
                sleep(Duration.ofSeconds(3L));
                log.info("I'm done with the bath");
            });

            future.get();
        }
    }

    //
    public static void main(String[] args) {
        runMyTasks();
    }
}
