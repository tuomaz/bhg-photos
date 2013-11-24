package se.bhg.photos.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.drew.imaging.ImageProcessingException;

import se.bhg.photos.exception.PhotoAlreadyExistsException;
import se.bhg.photos.model.Album;
import se.bhg.photos.model.AlbumPhoto;
import se.bhg.photos.model.Photo;
import se.bhg.photos.repository.AlbumRepository;
import se.bhg.photos.service.BhgV4ImporterService;
import se.bhg.photos.service.FileService;
import se.bhg.photos.service.PhotoService;

@Service
public class BhgV4ImporterServiceImpl implements BhgV4ImporterService{
    private static final Logger LOG = LoggerFactory.getLogger(BhgV4ImporterServiceImpl.class);
    private final static String BASE_PATH_V4 = "/home/fredrik/tmp/oldbhg";

    @Autowired
    JdbcTemplate jdbcTemplateImages;
    
    @Autowired
    FileService fileService;

    @Autowired
    PhotoService photoService;

    @Autowired
    AlbumRepository albumRepository;

    
    @Override
    public void importImagesAndGalleries() throws PhotoAlreadyExistsException {
        importGalleries();
        //importImages();
    }

    private void importGalleries() {
        long start = System.currentTimeMillis();
        int n = 0;
        List<Map<String, Object>> result = jdbcTemplateImages.queryForList("select * from gallery");
        for (Map<String, Object> row: result) {
            LOG.info("Found gallery!");
            Album album = new Album();
            album.setId(new ObjectId());
            album.setName((String) row.get("name"));
            album.setAlbumDate((Date) row.get("ts"));
            album.setCreatedDate(new Date());
            album.setOldId((int) row.get("id"));
            album.setOldParentId((int) row.get("pgallery"));
            album.setDescription((String) row.get("description"));
            LOG.debug("Trying to find photo with oldId = {}", album.getOldId());
            Photo photo = photoService.findByOldId(album.getOldId());
            if (photo != null) {
                album.setPhoto(photo.getId());
            }

            ArrayList<AlbumPhoto> albumPhotos = new ArrayList<>();
            List<Map<String, Object>> res = jdbcTemplateImages.queryForList("select * from image where gallery = " + album.getOldId());
            for (Map<String, Object> r: res) {
                int id = (int) r.get("id");
                Photo p = photoService.findByOldId(id);
                if (p != null) {
                    albumPhotos.add(new AlbumPhoto(p.getId(), (int) r.get("position")));
                }
            }
            if (albumPhotos.size() > 0) {
                album.setPhotos(albumPhotos);
            }
            Collections.sort(albumPhotos);
            albumRepository.save(album);
            n++;
        }
        long end = System.currentTimeMillis();
        LOG.info("Imported {} galleries in {} seconds ({} per second)", n, (end-start) / 1000, n / ((end-start) / 1000));
    }

    private void importImages() throws PhotoAlreadyExistsException {
        long  start = System.currentTimeMillis();
        List<Map<String, Object>> result = jdbcTemplateImages.queryForList("select * from image");
        int n = 0;
        for (Map<String, Object> row: result) {
            LOG.info("Found images!");
            String file = (String) row.get("filename");
            int lastSlash = file.lastIndexOf("/");
            String fileName = null;
            if (lastSlash < file.length() && lastSlash > 0) {
                fileName = file.substring(lastSlash + 1);
            } else {
            	LOG.error("Could not find slash in {}", file);
                continue;
            }
            
            String filePath = BASE_PATH_V4 + "/" + file;
            byte[] fileData;
            try {
                fileData = fileService.readFile(filePath);
            } catch (IOException e) {
                LOG.error("Could not read file {}: {}", filePath, e.getLocalizedMessage());
                e.printStackTrace();
                continue;
            }
            String uuid = UUID.randomUUID().toString();
            String username = (String) row.get("owner");
            String gallery = Integer.toString((int) row.get("gallery"));
            
            try {
                Photo photo = photoService.addPhoto(fileName, fileData, username, uuid, gallery);
                photo.setViews(((int) row.get("views")));
                photo.setOldId(((int) row.get("id")));
                photo.setImported(true);
                photoService.save(photo);
            } catch (ImageProcessingException | IOException | PhotoAlreadyExistsException e) {
                LOG.error("Could not add file {}: {}", filePath, e.getLocalizedMessage());
                e.printStackTrace();
                continue;
            }
            n++;
            /*
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                LOG.info(key + " = " + value);
            }
            */
        }
        long end = System.currentTimeMillis();
        LOG.info("Imported {} images in {}ms ({} images per second)", n, end-start, n / ((end-start)/1000));
    }
}
