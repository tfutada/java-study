package jp.futasoft.spring;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;

@SpringBootApplication
public class JavaSpringApplication {
    private static final Logger log = LoggerFactory.getLogger(JavaSpringApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringApplication.class, args);
    }

    @Bean
    @SneakyThrows
    CommandLineRunner runner() {
        return args -> {
            System.out.println("!!! started...");

            var n = Runtime.getRuntime().availableProcessors();
            log.info("!!! availableProcessors: {}", n);

            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                var process = executor.submit(() -> {
                    log.info("!!! Hello, World");
                });

                process.get();

            } catch (Exception e) {
                log.error("Error", e);
            }
        };
    }
}

@Controller
class HelloController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello, Worlduuu";
    }
}

@RestController
class HelloJsonController {
    @RequestMapping("/json")
    public Greeting home() {
        return new Greeting("Hello, Worlduuu");
    }

    public record Greeting(String message) {
    }
}