package bll.util;

// imports
import dk.easv.bll.util.ImageByteReader;

// JUnit imports
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

// java imports
import java.io.*;

// static imports
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author tomdra01, mrtng1
 */
public class ImageByteReaderTest {

    @DisplayName("Test image byte reader")
    @Test
    public void testReadImage() throws Exception {
        File tempFile = createTempImageFile();
        byte[] result = ImageByteReader.readImage(tempFile);

        assertNotNull(result);

        byte[] fileContent = readFileContent(tempFile);
        assertArrayEquals(fileContent, result);
    }

    private File createTempImageFile() throws IOException {
        File tempFile = File.createTempFile("image", ".tmp");

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(new byte[]{1, 2, 3, 4, 5});
        }
        return tempFile;
    }

    private byte[] readFileContent(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        }
    }
}
