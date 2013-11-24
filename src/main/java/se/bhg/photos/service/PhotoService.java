package se.bhg.photos.service;

import java.io.IOException;
import java.util.List;

import se.bhg.photos.exception.PhotoAlreadyExistsException;
import se.bhg.photos.model.AlbumPhoto;
import se.bhg.photos.model.Photo;

import com.drew.imaging.ImageProcessingException;

public interface PhotoService {
	Photo addPhoto(String originalFilename, byte[] binaryData, String uploader, String uuid) throws ImageProcessingException, IOException, PhotoAlreadyExistsException;
	Photo addPhoto(String originalFilename, byte[] binaryData, String uploader, String uuid, String directoryHint) throws ImageProcessingException, IOException, PhotoAlreadyExistsException;
	Photo getPhoto(String id);
	Iterable<Photo> getPhotos();
	void save(Photo photo);
	Photo findByOldId(int oldId);
	List<Photo> getAlbumPhotos(List<AlbumPhoto> photos);
}
