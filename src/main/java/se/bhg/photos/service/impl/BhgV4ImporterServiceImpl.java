package se.bhg.photos.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import se.bhg.photos.service.BhgV4ImporterService;

@Service
public class BhgV4ImporterServiceImpl implements BhgV4ImporterService{
    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplateImages;

    @Override
    public void importImagesAndGalleries() {
        importGalleries();
    }

    private void importGalleries() {
        List<Map<String, Object>> result = jdbcTemplateImages.queryForList("select * from gallery");
        for (Map<String, Object> row: result) {
            LOG.info("Found gallery!");
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                LOG.info(key + " = " + value);
            }
        }
    }

    private void importImages() {
        List<Map<String, Object>> result = jdbcTemplateImages.queryForList("select * from image");
        for (Map<String, Object> row: result) {
            LOG.info("Found gallery!");
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                LOG.info(key + " = " + value);
            }
        }
    }
}
