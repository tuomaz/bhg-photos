package se.bhg.photos.service;

import se.bhg.photos.model.Photo;

public interface FileService {
	void writeFile(Photo photo, byte[] data);
}
