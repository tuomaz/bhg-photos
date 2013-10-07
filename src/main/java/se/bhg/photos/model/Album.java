package se.bhg.photos.model;

import java.util.Date;
import java.util.Set;
import org.bson.types.ObjectId;

public class Album {
    private ObjectId id;
    private Set<AlbumPhoto> photos;
    private Date createdDate;
    private String name;
    private int year;
    private ObjectId parent;
    private ObjectId photo;
}
