package se.bhg.photos.exception;

public class PhotoAlreadyExistsException extends Exception{
    private static final long serialVersionUID = 3087874091725830194L;
    long checksum;
    String filename;

    public PhotoAlreadyExistsException(long checksum, String filename) {
        this.checksum = checksum;
        this.filename = filename;
    }
}
