package Transactions.exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@AllArgsConstructor
@Log4j2
public class AccountNotExistsException extends Exception {

    private String accountId;

    @Override
    public void printStackTrace() {
        log.error("Счет №{} не найден.", accountId);
    }

    @Override
    public String getMessage() {
        return "Счет №" + accountId + " не найден";
    }
}
