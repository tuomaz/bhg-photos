package se.bhg.photos.service;

import java.io.IOException;

import se.bhg.photos.model.Photo;

import com.drew.imaging.ImageProcessingException;

public interface PhotoService {
	void addPhoto(String originalFilename, byte[] binaryData, String uploader, String uuid) throws ImageProcessingException, IOException;
	Photo getPhoto(String id);
}
