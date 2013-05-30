package se.bhg.photos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.bhg.photos.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, String> {
	Photo findByUuid(String uuid);
}
