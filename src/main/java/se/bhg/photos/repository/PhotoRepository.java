package se.bhg.photos.repository;

import org.springframework.data.repository.CrudRepository;

import se.bhg.photos.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, String> {
	Photo findByUuid(String uuid);
}
