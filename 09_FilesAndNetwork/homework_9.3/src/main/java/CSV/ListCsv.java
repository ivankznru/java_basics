package CSV;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class ListCsv {
    private String id; //номер
    private String type; //тип
    private String currency; //валюта
    private LocalDate operationDate; //дата операции
    private String bankingReference; //референс проводки
    private String description; //описание оперции
    private String shortDescription; //краткое описание
    private double income; //приход
    private double flow; //расход


}
