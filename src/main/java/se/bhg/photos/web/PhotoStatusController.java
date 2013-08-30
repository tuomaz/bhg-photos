package se.bhg.photos.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.bhg.photos.model.Photo;
import se.bhg.photos.model.PhotoStatus;
import se.bhg.photos.service.FileService;
import se.bhg.photos.service.PhotoService;

@Controller
public class PhotoStatusController {
    //private static final Logger LOG = LoggerFactory.getLogger(PhotoStatusController.class);
    @Autowired
    FileService fileService;

    @Autowired
    PhotoService photoService;

    @ResponseBody
    @RequestMapping(value = "/photo/status/{id}", method = RequestMethod.GET)
    public PhotoStatus getStatus(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return PhotoStatus.UNKNOWN;
        }

        Photo photo = photoService.getPhoto(id);

        if (photo == null || photo.getStatus() == null) {
            return PhotoStatus.UNKNOWN;
        }

        return photo.getStatus();
    }
}
