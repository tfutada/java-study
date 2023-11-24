package jp.futasoft.photoz.web;

import jakarta.validation.Valid;
import jp.futasoft.photoz.PhotoNotFoundException;
import jp.futasoft.photoz.model.Photo;
import jp.futasoft.photoz.service.PhotozService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
class PhotozController {

    private final PhotozService photozService;

    @Autowired
    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
        // Initialize with some photos if necessary
        if (photozService.get().isEmpty()) {
            photozService.save(Photo.builder().id(1L).fileName("photo1.jpg").build());
            photozService.save(Photo.builder().id(2L).fileName("photo2.jpg").build());
            photozService.save(Photo.builder().id(3L).fileName("photo3.jpg").build());
        }
    }

    // a handler that takes a Photo as an arg.
    @PostMapping("/create") // POST should be used for creating resources
    public String handleCreate(@RequestBody @Valid Photo photo) {
        photozService.save(photo);
        return "Photo created: " + photo;
    }

    // a handle that delete an object specified by id
    @DeleteMapping("/delete/{id}") // DELETE should be used for deleting resources
    public String handleDelete(@PathVariable Long id) {
        Photo deletedPhoto = photozService.remove(id);
        return deletedPhoto != null ? "Photo deleted: id=" + id : "Photo not found";
    }

    // returns a list of Photo objects
    @GetMapping("/photos") // GET should be used for retrieving resources
    public Collection<Photo> handlePhotos() {
        return photozService.get();
    }

    // get a photo by id
    @GetMapping("/photo/{id}") // GET should be used for retrieving resources
    public Photo handlePhoto(@PathVariable Long id) {
        Photo photo = photozService.get(id);
        if (photo == null) {
            throw new PhotoNotFoundException("! ! ! Photo not found with id : " + id);
        }
        return photo;
    }
}
