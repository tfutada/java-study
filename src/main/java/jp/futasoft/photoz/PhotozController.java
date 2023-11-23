package jp.futasoft.photoz;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public String handleCreate(@RequestBody @Valid Photo photo) {
        // append a photo to the list
        photoList.add(photo);
        return "Photo created: " + photo;
    }

    // a handle that delete an object specified by id
    @RequestMapping("/delete/{id}")
    public String handleDelete(@PathVariable String id) {
        // remove a photo from the list
        photoList.removeIf(p -> p.getId().equals(id));
        return "Photo deleted: id=" + id;
    }

    // returns a list of Photo objects
    @RequestMapping("/photos")
    public List<Photo> handlePhotos() {
        return photoList;
    }

    // get a photo by id
    @RequestMapping("/photo/{id}")
    public Photo handlePhoto(@PathVariable String id) {
        return photoList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PhotoNotFoundException(id));
    }
}