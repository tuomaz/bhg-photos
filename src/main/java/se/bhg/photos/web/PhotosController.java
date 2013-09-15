package se.bhg.photos.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import se.bhg.photos.model.Photo;
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
    
    @RequestMapping("")
    public String index(Model model) {
    	LOG.debug("Accessed photos controller");
    	Iterable<Photo> photos = photoService.getPhotos();
    	model.addAttribute("photos", photos);
        return "photos";
    }
}