package Transactions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;
    @BeforeEach
    void setUp() {
        HashSet<Account> accounts = new HashSet<>();
        accounts.add(new Account("1", 15000));
        accounts.add(new Account("2", 130000));
        accounts.add(new Account("3", 2525000));

        Account blockedAccount = new Account("4", 15200);
        blockedAccount.setBlocked(true);
        accounts.add(blockedAccount);
        bank = new Bank(accounts);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("Попытки возврата несуществующего счета.")
    public void whenAccountIsNotExistThenReturnNull() {
        assertNull(bank.getAccount("12"));
    }

    @Test
    @DisplayName("Запрос баланса аккаунта.")
    public void getAccountBalanceTest() {
        assertEquals(15000, bank.getAccountBalance("1"));
    }


    @Test
    @DisplayName("Запрос баланса несуществующего счета.")
    public void notExistsAccountGetBalanceTest() {
        assertEquals(0, bank.getAccountBalance("24"));
    }

    @Test
    @DisplayName("Запрос баланса заблокированного счета.")
    public void blockedAccountGetBalanceTest() {
        assertEquals(15200, bank.getAccountBalance("4"));
    }


    @Test
    @DisplayName("Перевод когда сумма перевода больше нуля и счета не блокированы.")
    public void legalTransferTest() {
        bank.transfer("1", "2", 7500);
        assertEquals(7500, bank.getAccount("1").getBalance().get());
        assertEquals(137500, bank.getAccount("2").getBalance().get());
    }


    @Test
    @DisplayName("Перевод с блокированного счета.")
    public void transferFromBlockedAccountTest() {
        bank.transfer("4", "1", 7500);
        assertEquals(15200, bank.getAccount("4").getBalance().get());
        assertEquals(15000, bank.getAccount("1").getBalance().get());
    }


    @Test
    @DisplayName("Перевод на заблокированный счет.")
    public void transferToBlockedAccountTest() {
        bank.transfer("1", "4", 7500);
        assertEquals(15200, bank.getAccount("4").getBalance().get());
        assertEquals(15000, bank.getAccount("1").getBalance().get());
    }


    @Test
    @DisplayName("Перевод если сумма перевода равна 0.")
    public void transferZeroAmountTest() {
        bank.transfer("1","2",0);
        assertEquals(15000, bank.getAccount("1").getBalance().get());
        assertEquals(130000, bank.getAccount("2").getBalance().get());
    }


    @Test
    @DisplayName("Перевод если сумма перевода меньше нуля.")
    public void transferNegativeAmountTest() {
        bank.transfer("1","2",-500);
        assertEquals(15000, bank.getAccount("1").getBalance().get());
        assertEquals(130000, bank.getAccount("2").getBalance().get());
    }
}
