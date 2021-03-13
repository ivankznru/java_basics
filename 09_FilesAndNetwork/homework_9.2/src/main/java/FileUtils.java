import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static void copyFolder(String sourceDirectory, String destinationDirectory) throws IOException {
        /*
1. Используем the java.nio API
       Java NIO доступна начиная с Java 1.4.
       Java 7 представила NIO 2, которая принесла нам много полезных функций
       например, лучшая поддержка обработки символических ссылок, доступ к атрибутам файлов.
       Она также предоставила нам классы такие как
       Path, Paths и Files, которые значительно упростили работу с файловой системой.
*/
        Files.walk(Paths.get(sourceDirectory))
                .forEach(source -> {
                    Path destination = Paths.get(destinationDirectory, source.toString()
                            .substring(sourceDirectory.length()));
                    try {
                        Files.copy(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
       /*
        Прошли по дереву файлов с корнем в указанном исходном каталоге,
         используя Files.walk (), и вызвали Files.copy () для каждого файла или каталога,
         который нашли в исходном каталоге.
      */
/*

2. Используем the java.io API
       Java 7 стала поворотным моментом с точки зрения управления файловой системой,
          поскольку она представила множество новых удобных функций.
        Однако, если мы хотим оставаться совместимыми со старыми версиями Java,
         мы можем скопировать каталог, используя рекурсию и функции java.io.File:

   if (!destinationDirectory.exists()) {
                destinationDirectory.mkdir();
            }
            for (String f : sourceDirectory.list()) {
               copyDirectoryCompatibityMode(new File(sourceDirectory, f), new File(destinationDirectory, f));
            }

     В этом случае мы создадим каталог в целевом каталоге для каждого каталога в дереве исходных каталогов.
         Затем мы вызовем метод copyDirectoryCompatibityMode ():

        public static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
            if (source.isDirectory()) {
              copyDirectory(source, destination);
            } else {
                copyFile(source, destination);
            }
        }

        Также давайте посмотрим, как скопировать файл с помощью FileInputStream и FileOutputStream:


            try (InputStream in = new FileInputStream(sourceDirectory);
             OutputStream out = new FileOutputStream(destinationDirectory)) {
                byte[] buf = new byte[1024];
                int length;
               while ((length = in.read(buf)) > 0) {
                   out.write(buf, 0, length);
               }
            }
         }

 3. Используем Apache Commons IO
        Apache Commons IO имеет множество полезных функций, таких как служебные классы,
        фильтры файлов и компараторы файлов. Здесь мы будем использовать FileUtils,
         которые предоставляют методы для простого управления файлами и каталогами,
        то есть чтения, перемещения, копирования.

Сначала нужно добавить commons-io в наш файл pom.xml:
         <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.8.0</version>
         </dependency>

        Наконец, давайте скопируем каталог, используя этот подход:

          File sourceDirector = new File(sourceDirectory);
          File destinationDirector = new File(destinationDirectory);
          org.apache.commons.io.FileUtils.copyDirectory(sourceDirector, destinationDirector);


        Как показано в последнем примере, Apache Commons IO делает все намного проще,
        поскольку нам нужно только вызвать метод FileUtils.copyDirectory ().

 */
    }
}


