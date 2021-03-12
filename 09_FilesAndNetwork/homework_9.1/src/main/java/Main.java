import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG = LogManager.getRootLogger();
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("Введите путь к папке:");
                String path = new Scanner(System.in).nextLine();
                if (path != null && !path.isEmpty()) {
                    File folder = new File(path);
                    if (!folder.exists())
                        throw new FileNotFoundException();
                    System.out.println(FileUtils.getReadableSize_thenCorrect(FileUtils.calculateFolderSize(path)));
                }
            } catch (NullPointerException npe) {
                LOG.warn("В указанной папке нет файлов.");
            } catch (FileNotFoundException sfe) {
                LOG.error("Директория не существует. Указан ошибочный путь.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


















    }
}
