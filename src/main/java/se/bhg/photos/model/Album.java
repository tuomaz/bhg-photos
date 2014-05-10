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

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public int getOldId() {
        return oldId;
    }

    public void setOldId(final int oldId) {
        this.oldId = oldId;
    }

    public List<AlbumPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(final List<AlbumPhoto> photos) {
        this.photos = photos;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public ObjectId getParent() {
        return parent;
    }

    public void setParent(final ObjectId parent) {
        this.parent = parent;
    }

    public String getPhoto() {
        if (photo == null) {
            return null;
        }
        return photo.toString();
    }

    public void setPhoto(final ObjectId photo) {
        this.photo = photo;
    }

    public Date getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(final Date albumDate) {
        this.albumDate = albumDate;
    }

    public int getOldParentId() {
        return oldParentId;
    }

    public void setOldParentId(final int oldParentId) {
        this.oldParentId = oldParentId;
    }

    @Override
    public int compareTo(final Album o) {
        if (this.albumDate == null) {
            return -1;
        }
        if (o == null || o.getAlbumDate() == null) {
            return 1;
        }
        return this.albumDate.compareTo(o.getAlbumDate());
    }
}
