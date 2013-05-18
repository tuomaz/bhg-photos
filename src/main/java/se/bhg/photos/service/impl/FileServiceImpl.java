package se.bhg.photos.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import se.bhg.photos.model.FileType;
import se.bhg.photos.model.PhotoFile;
import se.bhg.photos.service.FileService;

public class FileServiceImpl implements FileService {
	@Value("${photos.store.path}")
	private String basePath;

	@Override
	public void writeFile(PhotoFile photoFile) {

		String separator = java.nio.file.FileSystems.getDefault().getSeparator();
		StringBuffer finalPath = new StringBuffer();
		
		finalPath.append(basePath);
		finalPath.append(separator);
		finalPath.append(photoFile.getPath());
		finalPath.append(separator);
		finalPath.append(photoFile.getFileName()); 
		File file = new File(finalPath.toString());
		
		try {
			FileUtils.writeByteArrayToFile(file, photoFile.getData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public FileType determinFileType(byte[] data) {
		Map<FileType, byte[]> headers = new HashMap<>();
		headers.put(FileType.JPG, new byte[] {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF});
		headers.put(FileType.PNG, new byte[] {(byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47, (byte) 0x0D, (byte) 0x0A, (byte) 0x1A, (byte) 0x0A});
		
		for(Map.Entry<FileType, byte[]> tuple : headers.entrySet()) {
			byte[] tempArray = new byte[tuple.getValue().length];
			System.arraycopy(data, 0, tempArray, 0, tempArray.length);
			if (Arrays.equals(tuple.getValue(), tempArray)) {
				return tuple.getKey();
			}
		}
		
		return FileType.UNKNOWN; 
	}
}
