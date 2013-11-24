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
import se.bhg.photos.repository.PhotoRepository;
import se.bhg.photos.service.PhotoService;

/**
 * @author Fredrik Tuomas
 * 
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
	private static final Logger LOG = LoggerFactory.getLogger(AlbumController.class);
	
    @Autowired
    PhotoService photoService;
    
    @Autowired
    AlbumRepository albumRepository; 
    
    @RequestMapping("")
    public String index(Model model) {
        LOG.debug("Accessed album controller");
        List<Album> albums = albumRepository.findAll();
        Collections.sort(albums);
        model.addAttribute("albums", albums);
        model.addAttribute("numAlbum", albums.size());
        return "listAlbums";
    }

    @RequestMapping("/{id}")
    public String album(Model model, @PathVariable String id) {
        LOG.debug("Accessed photos controller id");
        Album album = albumRepository.findOne(id);
        List<Photo> photos = photoService.getAlbumPhotos(album.getPhotos());
        model.addAttribute("album", album);
        return "showAlbum";
    }
}