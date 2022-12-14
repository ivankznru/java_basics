package Transactions.exceptions;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class IdenticalAccountsException extends Exception {

    @Override
    public void printStackTrace() {
        log.error("Указаны одинаковые номера счетов. Перевод средств невозможен.");
    }

    @Override
    public String getMessage() {
        return "Указаны одинаковые номера счетов. Перевод средств невозможен.";
    }
}
