package se.bhg.photos.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import se.bhg.photos.exception.PhotoAlreadyExistsException;
import se.bhg.photos.model.Photo;
import se.bhg.photos.model.PhotoStatus;
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
    public Photo addPhoto(String originalFilename, byte[] binaryData, String uploader, String uuid) throws ImageProcessingException, IOException, PhotoAlreadyExistsException {
        long checksum = getCRC32(binaryData);
        
        if (photoRepository.findByChecksum(checksum) != null) {
            LOG.error("Photo exists in database already, won't add it again. Checksum {}, filename {}.", checksum, originalFilename);
            throw new PhotoAlreadyExistsException(checksum, originalFilename);
        }
        
        InputStream is = new ByteArrayInputStream(binaryData);
        BufferedInputStream bis = new BufferedInputStream(is);
        Metadata metadata = metadataService.getMetaData(bis);

        Photo photo = new Photo();
        photo.setUploader(uploader);
        photo.setId(new ObjectId());
        photo.setStatus(PhotoStatus.PROCESSING);
        photo.setOriginalFilename(originalFilename);
        photo.setChecksum(checksum);
        photo.setId(new ObjectId());
        photo.setUploaded(new Date());
        LOG.debug("UUId = {}, id = ", uuid, photo.getId().toString());

        // photo.setMetadata(metadata);

        fileService.writeFile(photo, binaryData);

        // checkMetadata(metadata);

        photoRepository.save(photo);
        
        return photo;
    }

    public Photo getPhoto(String id) {
        return photoRepository.findOne(id);
    }
    
    public Iterable<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    private long getCRC32(byte[] data) {
        Checksum checksum = new CRC32();
        checksum.update(data, 0, data.length);
        return checksum.getValue();
    }

    private void checkMetadata(Metadata metadata) {
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                LOG.debug(tag.toString());
            }
        }
    }
}
