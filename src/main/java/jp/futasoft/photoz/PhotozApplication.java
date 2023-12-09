package jp.futasoft.photoz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@SpringBootApplication
public class PhotozApplication {
    private static final Logger log = LoggerFactory.getLogger(PhotozApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PhotozApplication.class, args);
        log.info("!!! started from PhotozApplication...");
    }
}

