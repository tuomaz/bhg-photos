package se.bhg.photos.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import se.bhg.photos.model.Album;
import se.bhg.photos.model.Photo;
import se.bhg.photos.repository.AlbumRepository;
import se.bhg.photos.service.PhotoService;

/**
 * @author Fredrik Tuomas
 * 
 */
@Controller
@RequestMapping("/photos")
public class PhotosController {
	private static final Logger LOG = LoggerFactory.getLogger(PhotosController.class);
	
    @Autowired
    PhotoService photoService;
    
    @Autowired
    AlbumRepository albumRepository; 
    
}
/*

<a th:each="photo : ${photos}" th:href="${'/bhg-photos/photo/' + photo.id + '/1200'}" th:title="${photo.filename}">
<img class="img-thumbnail" th:src="${'/bhg-photos/photo/' + photo.id + '/250'}" th:alt="${photo.filename}"/>
</a>
*/