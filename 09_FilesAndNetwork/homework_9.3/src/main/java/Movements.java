import CSV.ListCsv;
import lombok.extern.java.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Log
public class Movements {
    private List<ListCsv> opList;
    private TreeSet<String> opertypes;
    private static final DateTimeFormatter CSV_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");
    private double incomeSum;
    private double expenseSum;
    public Movements(String pathMovementsCsv) throws IOException {
        opList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(pathMovementsCsv)), 500);
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(br);
        for (CSVRecord record : csvRecords) {
            opList.add(new ListCsv(
                            record.get("Номер счета"),
                            record.get("Тип счёта"),
                            record.get("Валюта"),
                            LocalDate.parse(record.get("Дата операции"), CSV_DATE_FORMATTER),
                            record.get("Референс проводки"),
                            record.get("Описание операции"),
                            getShortDescription(record.get("Описание операции")),
                            getDouble(record.get("Приход")),
                            getDouble(record.get("Расход"))
                    )
            );
        }
        br.close();

       opertypes = opList.stream().map(ListCsv::getShortDescription).
               collect(Collectors.toCollection(TreeSet::new));

       incomeSum =opList.stream().mapToDouble(ListCsv::getIncome).sum();
       expenseSum =opList.stream().mapToDouble(ListCsv::getFlow).sum();

    }

    public void getExpenseGroup() {
        System.out.println("\nГруппировка по расходу: ");
        opertypes.forEach(operation -> System.out.println(operation + ": " + opList.stream()
                .filter(o -> o.getShortDescription().equals(operation) && o.getFlow() > 0)
                   .mapToDouble(ListCsv::getFlow).sum()));
    }
    public void getIncomeGroup(){
        System.out.println("\nГруппировка по приходу: ");
             opertypes.forEach(operation -> System.out.println(operation + ": " + opList.stream()
                   .filter(o -> o.getShortDescription().equals(operation) && o.getIncome() > 0)
                    .mapToDouble(ListCsv::getIncome).sum()));
    }

    public double getExpenseSum() {
     this.expenseSum= expenseSum;
     System.out.println("\nОбщий расход: " + expenseSum );
        return expenseSum;
    }

    public double getIncomeSum() {
        this.incomeSum =incomeSum;
        System.out.println("\nОбщий приход: " + incomeSum);
        return incomeSum;
    }

    public static double getDouble(String sum) {

        try {
            return Double.parseDouble(sum);
        } catch (NumberFormatException nfe) {
            log.info(sum + " --> Исправлен некорректный тип данных.");
            return Double.parseDouble(sum
                    .replaceAll("\"", "")
                    .replaceAll(",", "."));
        } catch (NullPointerException npe) {
            log.info("!> Не указана сумма для преобразования.");
            return 0.0;
        }
    }


    public static String getShortDescription(String fullDescription) {
        try {
            String[] tmpArr = fullDescription.split("\\s{3,}");
            return tmpArr[1].lastIndexOf("\\") >= 0 ?
                    tmpArr[1].substring(tmpArr[1].lastIndexOf("\\") + 1).trim().toUpperCase() :
                    tmpArr[1].substring(tmpArr[1].lastIndexOf("/") + 1).trim().toUpperCase();
        } catch (NullPointerException npe) {

            log.info("!> Полное описание не найдено.");
            return "нет краткого описания";
        }
    }
}




