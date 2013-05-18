package se.bhg.photos.service.impl;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import se.bhg.photos.service.MetadataService;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

@Service
public class MetadataServiceImpl implements MetadataService {

	@Override
	public Metadata getMetaData(BufferedInputStream bis) throws ImageProcessingException, IOException {
		Metadata metadata = ImageMetadataReader.readMetadata(bis, true);
		return metadata;
	}
}
