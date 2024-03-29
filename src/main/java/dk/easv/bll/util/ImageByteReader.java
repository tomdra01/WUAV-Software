package dk.easv.bll.util;

// java imports
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ImageByteReader {
    private static byte[] imageData;
    public static byte[] readImage(File selectedFile) throws Exception {
        if (selectedFile != null) {
            imageData = readBytesFromFile(selectedFile);
        }
        return imageData;
    }
    private static byte[] readBytesFromFile(File file) throws Exception {
        InputStream is = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        is.close();
        bos.close();
        return bos.toByteArray();
    }
}
