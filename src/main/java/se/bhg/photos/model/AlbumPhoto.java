package se.bhg.photos.model;

import org.bson.types.ObjectId;

public class AlbumPhoto implements Comparable<AlbumPhoto> {
    private ObjectId id;
    private ObjectId photo;
    private int position;
    
    public ObjectId getPhoto() {
        return photo;
    }

    public void setPhoto(ObjectId photo) {
        this.photo = photo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public AlbumPhoto(ObjectId id, int position) {
        this.photo = id;
        this.position = position;
    }

    @Override
    public int compareTo(AlbumPhoto o) {
        return this.position - o.getPosition();
    }
}
