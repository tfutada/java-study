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
    static void concurrentMorningRoutineUsingExecutorsWithName() {

        final ThreadFactory factory = Thread.ofVirtual().name("routine-", 0).factory();

        try (var executor = Executors.newThreadPerTaskExecutor(factory)) {

            var bathTime = executor.submit(() -> {
                log.info("I'm going to take a bath");
                try {
                    Thread.sleep(Duration.ofSeconds(3L)); // unmounted
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("I'm done with the bath");
            });

            var boilingWater = executor.submit(() -> {
                log.info("I'm going to boil some water");
                try {
                    sleep(Duration.ofSeconds(5L));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("I'm done with the water");
            });

            bathTime.get();
            boilingWater.get();
        }
    }

    //
    public static void main(String[] args) {
        concurrentMorningRoutineUsingExecutorsWithName();
    }
}
