package com.hobbymatcher.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileUtil {
    public static String transferFile(MultipartFile imageFile) {

        if (imageFile != null) {
            String filePath = "/var/lib/tomcat/webapps/hobbymatcher/images/";
            String originalFilename = imageFile.getOriginalFilename();
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String newFileName = UUID.randomUUID() + originalFilename;
            File targetFile = new File(filePath, newFileName);
            try {
                imageFile.transferTo(targetFile);
                return "http://hobbymatcher.com/images/" + newFileName;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
