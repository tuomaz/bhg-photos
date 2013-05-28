package se.bhg.photos.service;

import java.io.IOException;

import se.bhg.photos.model.Photo;

public interface FileService {
	void writeFile(Photo photo, byte[] data);
	byte[] readFile(String path) throws IOException;
}
