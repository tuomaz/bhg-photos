package se.bhg.photos.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import se.bhg.photos.service.PhotoService;

/**
 * @author Fredrik Tuomas
 * 
 */
@Controller
public class IndexController {
	@Autowired
	PhotoService photoSerivce;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST, params = "qquuid")
    @ResponseBody
    public String uploadFile(@RequestParam(value = "qquuid", required = true) String uuid, HttpServletRequest request, HttpSession session, HttpServletResponse response, Principal principal, Model model, Locale locale) throws IOException, ServletException {
     // from http://skillshared.blogspot.se/2012/08/java-class-for-valums-ajax-file.html
    	
       InputStream is = null;
       String filename = null; 
       String result = null;
       System.out.println("Starting file upload... uuid = " + uuid);
       try {
           
           if (isMultipartContent(request)) { 
                MultipartHttpServletRequest mrequest = (MultipartHttpServletRequest)request;
                Map<String, MultipartFile> fileMap = mrequest.getFileMap();           
                for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                   MultipartFile mfile = entry.getValue(); 
                   filename = mfile.getOriginalFilename();
                   int fileSize = (int) mfile.getSize();
                   byte[] fileContent = new byte[fileSize];
                   fileContent = mfile.getBytes();
                   photoSerivce.addPhoto(filename, fileContent, "fredrik");                 
                   break;
                }
           } else {
               filename = request.getHeader("X-File-Name");
               is = request.getInputStream();
           }
           
           result = "{\"success\":\"true\"}";
     
       } catch (Exception ex) {
           ex.printStackTrace();
           result = "{\"success\":\"false\"}";  
       } finally {
       } 
       System.out.println("Done upload...");
       return result;
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
}