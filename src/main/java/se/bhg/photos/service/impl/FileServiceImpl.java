package se.bhg.photos.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import se.bhg.photos.model.FileType;
import se.bhg.photos.model.Photo;
import se.bhg.photos.service.FileService;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);
    @Value("${photos.store.path}")
    private String basePath;

    @Override
    public void writeFile(Photo photo, byte[] data) {
        String separator = java.nio.file.FileSystems.getDefault().getSeparator();
        String normalizedFilename = normalizeFilename(photo.getOriginalFilename());
        String fileSuffix = determineFileType(data).toString();
        photo.setFilename(normalizedFilename + "." + fileSuffix);
        
        StringBuffer finalPath = new StringBuffer();
        finalPath.append(basePath);
        finalPath.append(separator);
        finalPath.append(getPath(photo));
        finalPath.append(separator);
        finalPath.append(photo.getUploader());
        finalPath.append(separator);
        finalPath.append(normalizedFilename);
        finalPath.append(".");
        finalPath.append(fileSuffix);
        File file = new File(finalPath.toString());
        LOG.debug("Full path is {}", file.getAbsolutePath());

        if (file.isFile()) {
        	LOG.error("File exists, bailing out.");
            return;
        }
        try {
            FileUtils.writeByteArrayToFile(file, data);
        } catch (IOException e) {
        	LOG.error("Could not write file {} to {}", file.getName(), file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    public FileType determineFileType(byte[] data) {
        Map<FileType, byte[]> headers = new HashMap<>();
        headers.put(FileType.JPG, new byte[] { (byte) 0xFF, (byte) 0xD8, (byte) 0xFF });
        headers.put(FileType.PNG, new byte[] { (byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47, (byte) 0x0D, (byte) 0x0A, (byte) 0x1A, (byte) 0x0A });

        for (Map.Entry<FileType, byte[]> tuple : headers.entrySet()) {
            byte[] tempArray = new byte[tuple.getValue().length];
            System.arraycopy(data, 0, tempArray, 0, tempArray.length);
            if (Arrays.equals(tuple.getValue(), tempArray)) {
                return tuple.getKey();
            }
        }

        return FileType.UNKNOWN;
    }

    private String normalizeFilename(final String filename) {
        StringBuffer sb = new StringBuffer();
        if (filename.indexOf(".") > -1) {
            sb.append(filename.substring(0, filename.lastIndexOf(".")));
        } else {
            sb.append(filename);
        }

        return sb.toString().replaceAll("åä", "a").replaceAll("ÅÄ", "A").replaceAll("ö", "o").replaceAll("Ö", "O").replaceAll("\\W+", "-");
    }

    private String getPath(Photo photo) {
    	String s = java.nio.file.FileSystems.getDefault().getSeparator();
    	DateTime dt = new DateTime();
    	DateTimeFormatter fmt = DateTimeFormat.forPattern("YYYY" + s + "MM" + s + "dd");	
        return fmt.print(dt);
    }
}
