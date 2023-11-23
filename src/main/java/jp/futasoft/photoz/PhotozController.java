package jp.futasoft.photoz;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class PhotozController {
    @RequestMapping("/hello")
    public String handle() {
        return "Hello in PhotozController";
    }

    // a handler that takes a Photo as an arg.
    @RequestMapping("/photo")
    public String handlePhoto(@RequestBody Photo photo) {
        return "Got photo " + photo;
    }

    @RequestMapping("/json")
    public Greeting home() {
        return new Greeting("Hello, Worlduuu");
    }

    public record Greeting(String message) {
    }
}