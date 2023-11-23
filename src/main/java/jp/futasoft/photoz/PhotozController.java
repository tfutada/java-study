package jp.futasoft.photoz;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
class PhotozController {

    private List<Photo> photoList = List.of(
            new Photo("1", "photo1.jpg"),
            new Photo("2", "photo2.jpg"),
            new Photo("3", "photo3.jpg")
    );

    @RequestMapping("/hello")
    public String handle() {
        return "Hello in PhotozController";
    }

    // a handler that takes a Photo as an arg.
    @RequestMapping("/photo")
    public String handlePhoto(@RequestBody Photo photo) {
        return "Got photo " + photo;
    }

    // returns a list of Photo objects
    @RequestMapping("/photos")
    public List<Photo> handlePhotos() {
        return photoList;
    }

    @RequestMapping("/json")
    public Greeting home() {
        return new Greeting("Hello, Worlduuu");
    }

    public record Greeting(String message) {
    }
}