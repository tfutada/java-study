package jp.futasoft.photoz;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class PhotozController {
    @RequestMapping("/hello")
    public String handle() {
        return "Hello in PhotozController";
    }

    @RequestMapping("/json")
    public Greeting home() {
        return new Greeting("Hello, Worlduuu");
    }

    public record Greeting(String message) {
    }
}