package jp.futasoft.photoz;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
class PhotozController {

    private List<Photo> photoList = new ArrayList<>();

    public PhotozController() {
        photoList.add(new Photo("1", "photo1.jpg"));
        photoList.add(new Photo("2", "photo2.jpg"));
        photoList.add(new Photo("3", "photo3.jpg"));
    }

    @RequestMapping("/hello")
    public String handle() {
        return "Hello in PhotozController";
    }

    // a handler that takes a Photo as an arg.
    @RequestMapping("/create")
    public String handleCreate(@RequestBody Photo photo) {
        // append a photo to the list
        photoList.add(photo);
        return "Photo created: " + photo;
    }

    // a handle that delete an object specified by id
    @RequestMapping("/delete")
    public String handleDelete(@ModelAttribute Photo photo) {
        // remove a photo from the list
        photoList.removeIf(p -> p.getId().equals(photo.getId()));
        return "Photo deleted: " + photo;
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