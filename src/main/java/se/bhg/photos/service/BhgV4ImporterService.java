package se.bhg.photos.service;

import se.bhg.photos.exception.PhotoAlreadyExistsException;

public interface BhgV4ImporterService {
    void importImagesAndGalleries() throws PhotoAlreadyExistsException;
}
