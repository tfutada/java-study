package jp.futasoft.photoz.repository;

import jp.futasoft.photoz.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhotozRepository extends JpaRepository<Photo, Long> {
}
