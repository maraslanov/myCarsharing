package app.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ImageUtil {
    public static boolean upload(MultipartFile imageFile, String path) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                byte[] bytes = imageFile.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
