package jp.futasoft.photoz;

public class PhotoNotFoundException extends RuntimeException {

    public PhotoNotFoundException(String id) {
        super("Photo not found with id: " + id);
    }
}

