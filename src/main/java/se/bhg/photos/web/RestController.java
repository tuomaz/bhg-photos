package se.bhg.photos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.bhg.photos.exception.PhotoAlreadyExistsException;
import se.bhg.photos.service.BhgV4ImporterService;

@Controller
@RequestMapping(value = "/rest")
public class RestController {
    @Autowired
    BhgV4ImporterService bhgV4ImporterService;

    @RequestMapping(value = "/importGalleries", method = RequestMethod.GET)
    @ResponseBody
    public final String importGallery() throws PhotoAlreadyExistsException {
        bhgV4ImporterService.importImagesAndGalleries();
        return "Ok";
    }
}
