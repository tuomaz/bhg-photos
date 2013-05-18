package se.bhg.photos.service;

import java.io.BufferedInputStream;
import java.io.IOException;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

public interface MetadataService {
	public Metadata getMetaData(BufferedInputStream bis) throws ImageProcessingException, IOException;
}
