package se.bhg.photos.model;

import org.joda.time.DateTime;

import com.drew.metadata.Metadata;

public class Photo {
	private byte[] photoData;
	private FileType fileType;
	private String uploader;
	private Metadata metadata;
	private DateTime uploaded;
	private DateTime shot;
	
	public byte[] getPhotoData() {
		return photoData;
	}
	public void setPhotoData(byte[] photoData) {
		this.photoData = photoData;
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
}
