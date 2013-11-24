package se.bhg.photos.repository;

import org.springframework.data.repository.CrudRepository;

import se.bhg.photos.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, String> {
    Photo findByChecksum(long checksum);
    Photo findByOldId(int oldId);
}
