package se.bhg.photos.model;

public class PhotoFile {
	private byte[] data;
	private String path;
	private String fileName;
	
	public PhotoFile(byte[] data, String path, String filename) {
	    this.data = data;
	    this.path = path;
	    this.fileName = filename;
	}
	
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
