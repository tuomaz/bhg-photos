package se.bhg.photos.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import javax.xml.bind.DatatypeConverter;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;

import se.bhg.photos.exception.PhotoAlreadyExistsException;
import se.bhg.photos.model.Photo;
import se.bhg.photos.model.PhotoStatus;
import se.bhg.photos.model.Metadata;
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
	public Photo addPhoto(String originalFilename, byte[] binaryData, String uploader, String uuid, String directoryHint) throws ImageProcessingException, IOException,
			PhotoAlreadyExistsException {
        long checksum = getCRC32(binaryData);
        String sha512 = DatatypeConverter.printHexBinary(getSHA512(binaryData));
        
        Photo existingPhoto = photoRepository.findByChecksum(checksum);
        if (existingPhoto != null) {
        	if (sha512.equalsIgnoreCase(existingPhoto.getSha512())) {
        		LOG.error("Photo exists in database already, won't add it again. Checksum {}, SHA-512 {} filename {}.", checksum, sha512, originalFilename);
        		throw new PhotoAlreadyExistsException(checksum, originalFilename);
        	}
        }
        
        InputStream is = new ByteArrayInputStream(binaryData);
        BufferedInputStream bis = new BufferedInputStream(is);
        com.drew.metadata.Metadata metadata = metadataService.getMetaData(bis);

        Photo photo = new Photo();
        photo.setUploader(uploader);
        photo.setId(new ObjectId());
        photo.setStatus(PhotoStatus.PROCESSING);
        photo.setOriginalFilename(originalFilename);
        photo.setChecksum(checksum);
        photo.setSha512(sha512);
        photo.setId(new ObjectId());
        photo.setUploaded(new Date());
        photo.setMetadata(getMetadata(metadata));
        LOG.debug("UUId = {}, id = ", uuid, photo.getId().toString());

        fileService.writeFile(photo, directoryHint, binaryData);

        photo.setMetadata(getMetadata(metadata));

        fileService.writeFile(photo, directoryHint, binaryData);

        photoRepository.save(photo);
        
        return photo;
	}
    
    @Override
    public Photo addPhoto(String originalFilename, byte[] binaryData, String uploader, String uuid) throws ImageProcessingException, IOException, PhotoAlreadyExistsException {
    	String directoryHint = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
    	return addPhoto(originalFilename, binaryData, uploader, uuid, directoryHint);
    }

    public Photo getPhoto(String id) {
        return photoRepository.findOne(id);
    }
    
    public Iterable<Photo> getPhotos() {
        return photoRepository.findAll();
    }
    
    public void save(Photo photo) {
    	photoRepository.save(photo);
    }

    private long getCRC32(byte[] data) {
        Checksum checksum = new CRC32();
        checksum.update(data, 0, data.length);
        return checksum.getValue();
    }
    
    private byte[] getSHA512(byte[] data) {
    	try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(data, 0, data.length);
			return md.digest();
			
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
    }

    /*
    private void checkMetadata(Metadata metadata) {
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                LOG.debug(tag.toString());
            }
        }
    }
    */

    private Metadata getMetadata(com.drew.metadata.Metadata metadata) {
        Metadata md = new Metadata();
        ExifSubIFDDirectory exif = metadata.getDirectory(ExifSubIFDDirectory.class);
        Date date = exif.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        md.setTimestamp(date);
        
        GpsDirectory gps = metadata.getDirectory(GpsDirectory.class);
        if (gps != null) {
            GeoLocation location = gps.getGeoLocation();
            md.setLongitude(location.getLongitude());
            md.setLatitude(location.getLatitude());
        }
        
        
        
        
        
        
        
        
        
        
        return md;
    }
}
