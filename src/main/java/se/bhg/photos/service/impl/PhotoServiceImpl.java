package se.bhg.photos.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;

import se.bhg.photos.model.FileType;
import se.bhg.photos.service.MetadataService;
import se.bhg.photos.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {
	//private final Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);

	@Autowired
	MetadataService metadataService;
	
	@Override
	public void addPhoto(String originalFilename, byte[] binaryData, String uploader) throws ImageProcessingException, IOException {
		String filename = normalizeFilename(originalFilename);
		
		System.out.println("Filtered filename is: " + filename);
		
		InputStream is = new ByteArrayInputStream(binaryData);
		BufferedInputStream bis = new BufferedInputStream(is);
		Metadata metadata = metadataService.getMetaData(bis);
		
		/*for (Directory directory : metadata.getDirectories()) {
		    for (Tag tag : directory.getTags()) {
		        System.out.println(tag);
		    }
		}*/		
	}
	
	private String normalizeFilename(final String filename) {
		StringBuffer sb = new StringBuffer();
		if (filename.indexOf(".") > -1) {
			sb.append(filename.substring(0, filename.lastIndexOf(".")));
		} else {
			sb.append(filename);
		}
		
		return sb.toString().
				replaceAll("åä", "a").
				replaceAll("ÅÄ", "A").
				replaceAll("ö", "o").
				replaceAll("Ö", "O").replaceAll("\\W+", "-");
	}
	
}
