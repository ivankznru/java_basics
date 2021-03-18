import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Log4j2
public class Main {
    /**
     * Адрес сайта для парсинга картинок.
     */
    private static final String WEB_URL = "https://www.lenta.ru";

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(WEB_URL).get();
        log.info("Connect to " + doc.title());
        Elements imagesURLs = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        imagesURLs.forEach(i -> {
            try {
                FileUtils.copyURLToFile(new URL(i.attr("src")),
                        new File("src/main/java/LentaImageDownloader/lenta.ru/" +
                                i.attr("src")
                                        .substring(i.attr("src").lastIndexOf("/"))));
                log.info(i.attr("src") + " --> " + "ok");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }
}
























//Цель задания
//
//Научиться читать файл CSV и анализировать его.
//
//Что нужно сделать
//
//Задание выполняйте в проекте
//09_FilesAndNetwork/homework_9.3
//
//
//Напишите код, который будет читать файл csv банковской выписки movementsList.csv и парсить полученные строки. Путь к файлу выписки храните в константе. Получение суммы расхода и дохода по всем операциями реализуйте в классе Movements, в методах getExpenseSum() и getIncomeSum() соответственно. Проверьте парсинг и получение сумм с помощью тестов.
//Класс Movements можете дополнять необходимыми методами для реализации решения.
//Код должен выводить сводную информацию по этой выписке: общий приход, общий расход и разбивку расходов.
//Примеры работы программы
//
//Сумма расходов: 398 563.39 руб.
//Сумма доходов: 289 890.06 руб.
//
//Суммы расходов по организациям:
//RUSMOSKVA56  SHLOVE REPUBLIC        1 081.53 руб.
//RUSMOSCOW42 SHCL ETOILE                     126.34 руб.
//RUSPUSHKINO105ZOOMAGAZIN 4             217.65 руб.
//
//
//Рекомендации
//
//Попробуйте представить одну запись выписки как объект, опишите его класс. При парсинге создавайте объекты из записи и работайте с ними.
//
//
//Критерии оценки
//
//«Зачёт» — после запуска программы в консоль выводятся суммы и расходы по организациям, тесты на получение суммы расхода и дохода выполняются успешно.
//«Незачёт» — задание не выполнено.