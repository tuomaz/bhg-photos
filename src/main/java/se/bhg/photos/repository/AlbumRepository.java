package se.bhg.photos.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import se.bhg.photos.model.Album;

public interface AlbumRepository extends CrudRepository<Album, String> {
    List<Album> findAll();
}
