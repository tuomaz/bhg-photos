package se.bhg.photos.model;

import org.bson.types.ObjectId;

public class AlbumPhoto implements Comparable<AlbumPhoto> {
    private ObjectId id;
    private ObjectId photo;
    private int position;

    public ObjectId getPhoto() {
        return photo;
    }

    public String getPhotoIdText() {
        return photo.toString();
    }

    public void setPhoto(final ObjectId photo) {
        this.photo = photo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(final int position) {
        this.position = position;
    }

    public AlbumPhoto(final ObjectId id, final int position) {
        this.photo = id;
        this.position = position;
    }

    @Override
    public int compareTo(final AlbumPhoto o) {
        return this.position - o.getPosition();
    }
}
