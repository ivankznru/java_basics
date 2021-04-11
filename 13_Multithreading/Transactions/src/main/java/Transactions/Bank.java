package Transactions;

import Transactions.exceptions.AccountNotExistsException;
import Transactions.exceptions.BlockedAccountException;
import Transactions.exceptions.IdenticalAccountsException;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;


@Log4j2
public class Bank {

    private final Random random = new Random();
    private HashSet<Account> accounts;

    public Bank(HashSet<Account> accountSet) {
        accounts = accountSet;
    }


    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum)
            throws InterruptedException {
        log.info("операция {} отправлена на проверку.", UUID.randomUUID().toString());
        Thread.sleep(1000);
        return random.nextBoolean();
    }


    public Account getAccount(String accountId) {
        try {
            if (isAccountExists(accountId))
                return accounts.stream()
                        .filter(account -> account.getAccountID().equals(accountId)).findAny().orElseThrow();
        } catch (AccountNotExistsException ane) {
            System.out.println(ane.getMessage());
        }
        return null;
    }


    public synchronized void transfer(String senderAccount, String recipientAccount, long amount) {
        try {
            if (getAccount(recipientAccount).isBlocked()) {
                throw new BlockedAccountException(recipientAccount);
            }
            if (senderAccount.equals(recipientAccount)) {
                throw new IdenticalAccountsException();
            }
            getAccount(senderAccount).withdraw(amount);
            getAccount(recipientAccount).replenish(amount);
            if (amount > 50000) {
                if (isFraud(senderAccount, recipientAccount)) {
                    getAccount(senderAccount).setBlocked(true);
                    getAccount(recipientAccount).setBlocked(true);
                    log.warn("Операция не прошла проверку.\nСчета заблокированы.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public long getAccountBalance(String accountId) {
        try {
            return getAccount(accountId).getBalance().get();
        } catch (NullPointerException npe) {
            return 0;
        }
    }


    private boolean isAccountExists(String accountId) throws AccountNotExistsException {
        if (accounts.stream().anyMatch(account -> account.getAccountID().equals(accountId))) {
            return true;
        } else {
            throw new AccountNotExistsException(accountId);
        }
    }
}
