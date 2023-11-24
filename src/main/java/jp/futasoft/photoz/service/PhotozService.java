package jp.futasoft.photoz.service;

import jp.futasoft.photoz.model.Photo;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class PhotozService {

    private final Map<String, Photo> db = new HashMap<String, Photo>() {{
        put("1", new Photo("1", "hello.jpg"));
    }};

    public Collection<Photo> get() {
        return db.values();
    }

    public Photo get(String id) {
        return db.get(id);
    }

    public Photo remove(String id) {
        return db.remove(id);
    }

    public void save(String id, Photo photo) {
        db.put(id, photo);
    }
}
