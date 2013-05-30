package se.bhg.photos.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import com.drew.metadata.Metadata;

public class Photo {
    @Id
	private ObjectId id;
	private FileType fileType;
	private String uploader;
	private String filename;
	private String originalFilename;
	private String path;
	private Metadata metadata;
	private Date uploaded;
	private Date shot;
	private long checksum;
	
	public long getChecksum() {
        return checksum;
    }
    public void setChecksum(long checksum) {
        this.checksum = checksum;
    }
    public FileType getPhotoType() {
		return fileType;
	}
	public void setPhotoType(FileType fileType) {
		this.fileType = fileType;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
    public FileType getFileType() {
        return fileType;
    }
    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getOriginalFilename() {
        return originalFilename;
    }
    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }
    public Date getUploaded() {
        return uploaded;
    }
    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }
    public Date getShot() {
        return shot;
    }
    public void setShot(Date shot) {
        this.shot = shot;
    }
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
}
