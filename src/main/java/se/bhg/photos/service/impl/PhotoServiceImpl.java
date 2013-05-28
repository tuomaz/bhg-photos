package se.bhg.photos.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

import se.bhg.photos.model.Photo;
import se.bhg.photos.service.FileService;
import se.bhg.photos.service.MetadataService;
import se.bhg.photos.service.PhotoService;
import se.bhg.photos.repository.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService {
	private static final Logger LOG = LoggerFactory.getLogger(PhotoServiceImpl.class);

    @Autowired
    MetadataService metadataService;

    @Autowired
    FileService fileService;

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public void addPhoto(String originalFilename, byte[] binaryData, String uploader, String uuid) throws ImageProcessingException, IOException {

        InputStream is = new ByteArrayInputStream(binaryData);
        BufferedInputStream bis = new BufferedInputStream(is);
        Metadata metadata = metadataService.getMetaData(bis);

        Photo photo = new Photo();
        photo.setOriginalFilename(originalFilename);
        photo.setChecksum(getCRC32(binaryData));
        photo.setUuid(uuid);
        LOG.debug("UUId = {}", uuid);
        
        // photo.setMetadata(metadata);

        fileService.writeFile(photo, binaryData);

        photoRepository.save(photo);

        /*
         * for (Directory directory : metadata.getDirectories()) { for (Tag tag
         * : directory.getTags()) { System.out.println(tag); } }
         */
    }
    
    public Photo getPhoto(String uuid) {
    	return photoRepository.findByUuid(uuid);
    }

    private long getCRC32(byte[] data) {
        Checksum checksum = new CRC32();
        checksum.update(data, 0, data.length);
        return checksum.getValue();
    }
    
}
