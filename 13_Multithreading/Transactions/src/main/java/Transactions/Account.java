package Transactions;

import Transactions.exceptions.ReplenishNotPossibleException;
import Transactions.exceptions.WithdrawNotPossibleException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;


@Data
@AllArgsConstructor
@Log4j2
public class Account {


    private String accountID;

    private AtomicLong balance;

    private boolean blocked;

    public Account(@NonNull String accountID, long balance) {
        this.accountID = accountID;
        this.balance = new AtomicLong(balance);
    }


    public boolean replenish(long amount) throws ReplenishNotPossibleException {
        if (isLegalOperation(amount)) {
            balance.addAndGet(amount);
            log.info("пополнение счета {} на сумму {}",accountID, amount);
            return true;
        } else {
            throw new ReplenishNotPossibleException(accountID);
        }
    }


    public boolean withdraw(long amount) throws WithdrawNotPossibleException {
        if (isLegalOperation(amount) && balance.get() > amount) {
            balance.accumulateAndGet(amount, (x, y) -> x - y);
            log.info("списание со счета {} на сумму {}", accountID, amount);
            return true;
        } else {
            throw new WithdrawNotPossibleException(accountID);
        }
    }



    private boolean isLegalOperation(long amount) {
        return !blocked && amount > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getAccountID().equals(account.getAccountID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountID());
    }
}
