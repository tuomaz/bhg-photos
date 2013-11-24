package se.bhg.photos.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class Album implements Comparable<Album> {
    private ObjectId id;
    private int oldId;
    private List<AlbumPhoto> photos;
    private Date createdDate;
    private Date albumDate;
    private String name;
    private int year;
    private ObjectId parent;
    private int oldParentId;
    private ObjectId photo;
    private String description;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public int getOldId() {
        return oldId;
    }
    public void setOldId(int oldId) {
        this.oldId = oldId;
    }
    public List<AlbumPhoto> getPhotos() {
        return photos;
    }
    public void setPhotos(List<AlbumPhoto> photos) {
        this.photos = photos;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public ObjectId getParent() {
        return parent;
    }
    public void setParent(ObjectId parent) {
        this.parent = parent;
    }
    public ObjectId getPhoto() {
        return photo;
    }
    public void setPhoto(ObjectId photo) {
        this.photo = photo;
    }
    public Date getAlbumDate() {
        return albumDate;
    }
    public void setAlbumDate(Date albumDate) {
        this.albumDate = albumDate;
    }
    public int getOldParentId() {
        return oldParentId;
    }
    public void setOldParentId(int oldParentId) {
        this.oldParentId = oldParentId;
    }
    @Override
    public int compareTo(Album o) {
        if (this.albumDate == null) {
            return -1;
        }
        if (o == null || o.getAlbumDate() == null) {
            return 1;
        }
        return this.albumDate.compareTo(o.getAlbumDate());
    }
}
