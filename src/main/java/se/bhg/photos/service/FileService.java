package se.bhg.photos.service;

import se.bhg.photos.model.FileType;
import se.bhg.photos.model.PhotoFile;

public interface FileService {
	void writeFile(PhotoFile photoFile);
	FileType determinFileType(byte[] data);
}
