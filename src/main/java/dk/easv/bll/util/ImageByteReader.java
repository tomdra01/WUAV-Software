package dk.easv.bll.util;

import javafx.stage.FileChooser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImageByteReader {
    private byte[] imageData;
    public byte[] getImage(File selectedFile) throws Exception {
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
