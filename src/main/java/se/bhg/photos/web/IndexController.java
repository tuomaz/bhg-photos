package se.bhg.photos.web;

import java.io.IOException;
import java.security.Principal;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import se.bhg.photos.model.Photo;
import se.bhg.photos.service.PhotoService;

/**
 * @author Fredrik Tuomas
 * 
 */
@Controller
public class IndexController {
    @Autowired
    PhotoService photoSerivce;

    @RequestMapping("/")
    public String first() {
        return "index";
    }
    
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/upload")
    public String upload() {
        return "upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, params = "qquuid")
    @ResponseBody
    public Answer uploadFile(@RequestParam(value = "qquuid", required = true) String uuid, HttpServletRequest request, HttpSession session, HttpServletResponse response, Principal principal,
            Model model, Locale locale) throws IOException, ServletException {
        // from
        // http://skillshared.blogspot.se/2012/08/java-class-for-valums-ajax-file.html
        String filename = null;
        boolean success = false;
        String id = "";
        try {

            if (isMultipartContent(request)) {
                MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = mrequest.getFileMap();
                for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                    MultipartFile mfile = entry.getValue();
                    filename = mfile.getOriginalFilename();
                    int fileSize = (int) mfile.getSize();
                    byte[] fileContent = new byte[fileSize];
                    fileContent = mfile.getBytes();
                    Photo photo = photoSerivce.addPhoto(filename, fileContent, "fredrik", uuid);
                    success = true;
                    id = photo.getId().toString();
                    break;
                }
            } else {
                filename = request.getHeader("X-File-Name");
                //is = request.getInputStream();
                // TODO take care of this case...
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        System.out.println("Done upload...");
        return new Answer(success, id);
    }

    private static final boolean isMultipartContent(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType == null) {
            return false;
        }
        if (contentType.toLowerCase().startsWith("multipart/")) {
            return true;
        }
        return false;
    }

    public class Answer {
        boolean success;
        String id;
        public boolean isSuccess() {
            return success;
        }
        public void setSuccess(boolean success) {
            this.success = success;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public Answer(boolean s, String i) {
            success = s;
            id = i;
        }
    }
}