package jp.futasoft.photoz.service;

import jp.futasoft.photoz.model.Photo;
import jp.futasoft.photoz.repository.PhotozRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;

@Service
public class PhotozService {

    @Autowired
    private PhotozRepository photozRepository;

    public Collection<Photo> get() {
        return photozRepository.findAll();
    }

    public Photo get(Long id) {
        return photozRepository.findById(id).orElse(null);
    }

    public Photo remove(Long id) {
        var photo = get(id);
        photozRepository.delete(photo);
        return photo;
    }

    public void save(Photo photo) {
        photozRepository.save(photo);
    }
}
