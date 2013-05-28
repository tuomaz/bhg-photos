package se.bhg.photos.model;

import org.joda.time.DateTime;

import com.drew.metadata.Metadata;

public class Photo {
	private String uuid;
	private FileType fileType;
	private String uploader;
	private String filename;
	private String originalFilename;
	private String path;
	private Metadata metadata;
	private DateTime uploaded;
	private DateTime shot;
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
    public DateTime getUploaded() {
        return uploaded;
    }
    public void setUploaded(DateTime uploaded) {
        this.uploaded = uploaded;
    }
    public DateTime getShot() {
        return shot;
    }
    public void setShot(DateTime shot) {
        this.shot = shot;
    }
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
