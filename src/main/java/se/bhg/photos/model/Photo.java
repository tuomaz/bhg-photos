package se.bhg.photos.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Photo {
    @Id
    private ObjectId id;
    private int oldId;
    private int views;
    private FileType fileType;
    private String uploader;
    private String filename;
    private String originalFilename;
    private String path;
    private Metadata metadata;
    private Date uploaded;
    private Date shot;
    private long checksum;
    private String sha512;
    private PhotoStatus status;
    private boolean imported;

    public long getChecksum() {
        return checksum;
    }

    public void setChecksum(final long checksum) {
        this.checksum = checksum;
    }

    public FileType getPhotoType() {
        return fileType;
    }

    public void setPhotoType(final FileType fileType) {
        this.fileType = fileType;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(final String uploader) {
        this.uploader = uploader;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(final Metadata metadata) {
        this.metadata = metadata;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(final FileType fileType) {
        this.fileType = fileType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(final String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public Date getUploaded() {
        return uploaded;
    }

    public void setUploaded(final Date uploaded) {
        this.uploaded = uploaded;
    }

    public Date getShot() {
        return shot;
    }

    public void setShot(final Date shot) {
        this.shot = shot;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public PhotoStatus getStatus() {
        return status;
    }

    public void setStatus(final PhotoStatus status) {
        this.status = status;
    }

    public int getOldId() {
        return oldId;
    }

    public void setOldId(final int oldId) {
        this.oldId = oldId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(final int views) {
        this.views = views;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(final boolean imported) {
        this.imported = imported;
    }

    public String getSha512() {
        return sha512;
    }

    public void setSha512(final String sha512) {
        this.sha512 = sha512;
    }
}
