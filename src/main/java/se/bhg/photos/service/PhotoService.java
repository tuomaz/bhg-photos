package se.bhg.photos.service;

import java.io.IOException;

import com.drew.imaging.ImageProcessingException;

public interface PhotoService {
	void addPhoto(String originalFilename, byte[] binaryData, String uploader) throws ImageProcessingException, IOException;
}
