package Transactions.exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@AllArgsConstructor
@Log4j2
public class BlockedAccountException extends Exception {

    private String accountId;

    @Override
    public void printStackTrace() {
        log.error("Счет №{} заблокирован. Перевод средств невозможен.", accountId);
    }

    @Override
    public String getMessage() {
        return "Cчет №" + accountId + " заблокирован. Перевод средств невозможен";
    }
}
