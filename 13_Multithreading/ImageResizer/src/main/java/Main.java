

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final static String SOURCE_FOLDER_PATH = "src/main/resources/src";
    ;
    private final static String RESULT_FOLDER_PATH = "src/main/resources/dst";
    ;
    private final static int SIZE = 300;

    public static void main(String[] args) {
        try {
            final File sourceFolder = createFolder(SOURCE_FOLDER_PATH);
            final File destinationFolder = createFolder(RESULT_FOLDER_PATH);

            final AtomicInteger counter = new AtomicInteger(0);

            assert sourceFolder != null;
            assert destinationFolder != null;
            List<File> picList = sortedFilesJpdPng(SOURCE_FOLDER_PATH);

            picList.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / divider(picList))).values()

                    .forEach(picsList -> new ImageResizer(picsList, RESULT_FOLDER_PATH, SIZE).run());
        } catch (NullPointerException | IOException e) {
            LOGGER.error("Произошла ошибка " + e);
            System.out.println("Некорректные параметры запуска.");
        }

    }


    private static List<File> sortedFilesJpdPng(String srcFolder) throws IOException {
        return Files.walk(Paths.get(srcFolder)).map(Path::toFile).filter(file -> !((File) file).isDirectory())
                .filter(f -> ImageResizer.getFileExtension(f).equals("jpg")
                        || ImageResizer.getFileExtension(f).equals("JPG")
                        || ImageResizer.getFileExtension(f).equals("png")
                        || ImageResizer.getFileExtension(f).equals("PNG")
                        || ImageResizer.getFileExtension(f).equals("JPEG")
                        || ImageResizer.getFileExtension(f).equals("BMP"))
                .sorted(Comparator.comparing(File::length)).collect(Collectors.toList());

    }


    public static int getNumbersOfCores() {
        int countOfCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Число ядер процессора :  " + countOfCores);
        return countOfCores;
    }

    private static int divider(List<File> files) {
        return files != null && files.size() > 0 ? (files.size() - files.size() % getNumbersOfCores()) / getNumbersOfCores() : -1;
    }


    private static File createFolder(String path) {
        File result = new File(path);
        if (result.exists() && result.isDirectory()) {
            return result;
        }
        return null;
    }


}



/*

Imgscalr использует java.awt.Graphics2D в фоновом режиме. У него простой API с несколькими различными методами изменения размера изображения.
Imgscalr предоставляет нам либо лучший результат, либо самый быстрый результат, либо сбалансированный результат в зависимости от выбранного
нами варианта масштабирования. Также доступны другие функции обработки изображений - например, функции обрезки и поворота.
Сначала добавим следующую зависимость Maven:

<dependency>
   <groupId>org.imgscalr</groupId>
    <artifactId>imgscalr-lib</artifactId>
    <version>4.2</version>
</dependency>

Библиотека также позволяет использовать несколько параметров конфигурации и обрабатывает прозрачность изображения в фоновом режиме.

Наиболее важные параметры:

mode - используется для определения режимов изменения размера, которые будет использовать алгоритм. Например, мы можем определить,
 хотим ли мы сохранить пропорции изображения (варианты: AUTOMATIC, FIT_EXACT, FIT_TO_HEIGHT и FIT_TO_WIDTH)
метод - указывает процессу изменения размера, чтобы его внимание было сосредоточено на скорости, качестве или и том и другом.
 Возможные значения: AUTOMATIC, BALANCED, QUALITY, SPEED, ULTRA_QUALITY.
Также можно определить дополнительные свойства изменения размера, которые обеспечат нам ведение журнала
 или укажут библиотеке для внесения некоторых изменений цвета в изображение (сделать его светлее, темнее, оттенки серого и т. Д.).


Imgscalr работает со всеми файлами, поддерживаемыми Java Image IO - JPG, BMP, JPEG, WBMP, PNG и GIF.

*/