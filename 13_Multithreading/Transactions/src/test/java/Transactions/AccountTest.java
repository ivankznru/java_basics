package Transactions;

import Transactions.exceptions.BlockedAccountException;
import Transactions.exceptions.ReplenishNotPossibleException;
import Transactions.exceptions.WithdrawNotPossibleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account acc = new Account("1", 1000);
    Account acc1 = new Account("2", 2000);
    Account acc2 = new Account("3", 3000);


    @BeforeEach
    void setUp() {
        acc2.setBlocked(true);
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    @DisplayName("Пополнения счета когда сумма 0.")
    public void whenAmount0ThenReplenishThrowsWithdrawNotPossibleException()
    {Exception exception = assertThrows(ReplenishNotPossibleException.class,() ->{acc.replenish(0);});

    }


    @Test
    @DisplayName("Пополнения счета когда сумма меньше нуля.")
    public void whenAmountNegativeThenReplenishThrowsWithdrawNotPossibleException()
    {Exception exception = assertThrows(ReplenishNotPossibleException.class,() ->{acc.replenish(-500);});

    }



    @Test
    @DisplayName("Пополнения заблокированного счета")
    public void whenAccountBlockedThenReplenishThrowsWithdrawNotPossibleException()
    {Exception exception = assertThrows(ReplenishNotPossibleException.class,() ->{acc2.replenish(500);});

    }


    @Test
    @DisplayName("Пополнения счета когда сумма больше нуля")
    public void whenAmountMoreThan0ThenReplenishOk() throws ReplenishNotPossibleException {
        acc.replenish(600);
        assertEquals(1600, acc.getBalance().intValue());
    }


    @Test
    @DisplayName("Пополнения счета в многопоточной среде.")
    public void testReplenishBySomeThreads() {
        CompletableFuture.runAsync(() -> {
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                threads.add(new Thread(() -> {
                    try {
                        acc1.replenish(10);
                    } catch (ReplenishNotPossibleException e) {
                        e.printStackTrace();
                    }
                }));
            }
            threads.forEach(Thread::start);
            assertEquals(3000, acc1.getBalance().intValue());
        });
    }


    @Test
    @DisplayName("Списания со счета когда сумма 0")
    public void whenAmount0ThenThrowWithdrawOperationNotPossibleException()
    {Exception exception = assertThrows(WithdrawNotPossibleException.class,() ->{acc.withdraw(0);});

    }


    @Test
    @DisplayName("Списания со счета когда сумма меньше нуля")
    public void whenAmountNegativeThenThrowWithdrawOperationNotPossibleException() {
        Exception exception = assertThrows(WithdrawNotPossibleException.class, () -> {
            acc.withdraw(-500);
        });
    }

    @Test
    @DisplayName("Списания со счета когда сумма больше нуля и меньше баланса счета и счет не заблокирован.")
    public void whenAmountMoreThan0ThenWithdrawOk() throws WithdrawNotPossibleException {
        acc.withdraw(600);
        assertEquals(400, acc.getBalance().intValue());
    }



    @Test
    @DisplayName("Списания если сумма списания больше баланса счета.")
    public void whenAmountBiggerThenThrowWithdrawOperationNotPossibleException() {
        Exception exception = assertThrows(WithdrawNotPossibleException.class, () -> {
            acc.withdraw(2000);
        });
    }



    @Test
    @DisplayName("Списания если счет заблокирован.")
    public void whenAccountBlockedThenThrowWithdrawOperationNotPossibleException() {
        Exception exception = assertThrows(WithdrawNotPossibleException.class, () -> {
            acc2.withdraw(2000);
        });
    }

    @Test
    @DisplayName("Списания со счета в многопоточной среде.")
    public void testWithdrawBySomeThreads() {
        CompletableFuture.runAsync(() -> {
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                threads.add(new Thread(() -> {
                    try {
                        acc1.withdraw(10);
                    } catch (WithdrawNotPossibleException e) {
                        e.printStackTrace();
                    }
                }));
            }
            threads.forEach(Thread::start);
            assertEquals(4000, acc1.getBalance().intValue());
        });
    }
}