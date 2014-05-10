package se.bhg.photos.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.bhg.photos.exception.PhotoAlreadyExistsException;
import se.bhg.photos.model.Album;
import se.bhg.photos.repository.AlbumRepository;
import se.bhg.photos.service.BhgV4ImporterService;

@Controller
@RequestMapping(value = "/rest")
public class RestController {
    @Autowired
    BhgV4ImporterService bhgV4ImporterService;

    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping(value = "/importGalleries", method = RequestMethod.GET)
    @ResponseBody
    public final String importGallery() throws PhotoAlreadyExistsException {
        bhgV4ImporterService.importImagesAndGalleries();
        return "Ok";
    }

    @ResponseBody
    @RequestMapping(value = "/album/list", method = RequestMethod.GET)
    public final List<Album> listAlbums() {
        return albumRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/album/get/{id}", method = RequestMethod.GET)
    public final Album getAlbum(@PathVariable final String id) {
        return albumRepository.findOne(id);
    }
}
