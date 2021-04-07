import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;


public class ImageResizer implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(ImageResizer.class);
    private List<File> files;
    private String dstFolder;
    private int size;

    public ImageResizer(List<File> files, String dstFolder, int size) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.size = size;
    }


    public void run() {
        long start = System.currentTimeMillis();
        try {
            for (File file : files) {
                BufferedImage srcImage = ImageIO.read(file);
                BufferedImage scaledImage = Scalr.resize(srcImage, Scalr.Method.QUALITY, size);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(scaledImage, getFileExtension(newFile), newFile);
                LOGGER.info("Файл: " + newFile.getName() + " уменьшен до размера " + size);
            }
            System.out.println("Файлов скопировано: " + files.size() + " Заняло времени: " + (System.currentTimeMillis() - start));
        } catch (Exception ex) {
            LOGGER.error("Возникла ошибка: " + ex);
        }
    }

    //метод определения расширения файла
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }
}
