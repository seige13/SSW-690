package com.hobbymatcher.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileUtil {
    public static String transferFile(MultipartFile imageFile) {

        if (imageFile != null) {
            String filePath = "/var/lib/tomcat/webapps/hobbymatcher";
            String originalFilename = imageFile.getOriginalFilename();
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String newFileName = UUID.randomUUID() + originalFilename;
            File targetFile = new File(filePath, newFileName);
            try {
                imageFile.transferTo(targetFile);
                return targetFile.getAbsolutePath();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
