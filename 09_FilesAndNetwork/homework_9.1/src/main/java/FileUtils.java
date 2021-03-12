import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class FileUtils {

    public static long calculateFolderSize(String path) throws IOException {

    Path folder = Paths.get(path);
    long size = Files.walk(folder)
            .filter(p -> p.toFile().isFile())
            .mapToLong(p -> p.toFile().length())
            .sum();
        return size;
                }

//   File folder = new File(path);
//   long size = org.apache.commons.io.FileUtils.sizeOfDirectory(folder);

//      File folder = new File(path);

//     Iterable<File> files = Files.fileTraverser().breadthFirst(folder);
//      long size = StreamSupport.stream(files.spliterator(), false) .filter(f -> f.isFile())
//             .mapToLong(File::length).sum();

//      return size;
//   }

public static String getReadableSize_thenCorrect(long size) throws IOException {

        String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int unitIndex = (int) (Math.log10(size) / 3);
        double unitValue = 1 << (unitIndex * 10);

        String readableSize = new DecimalFormat("#,##0.#")
        .format(size / unitValue) + " "
        + units[unitIndex];
        return readableSize;
        }
        }

