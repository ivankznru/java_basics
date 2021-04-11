package Transactions;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Simulation {

    public static void main(String[] args) {

        HashSet<Account> accounts = new HashSet<>();

        accounts.add(new Account("0", 120000));
        accounts.add(new Account("1", 135000));
        accounts.add(new Account("2", 156000));
        accounts.add(new Account("3", 320000));
        accounts.add(new Account("4", 400000));
        accounts.add(new Account("5", 700000));
        accounts.add(new Account("6", 800000));
        accounts.add(new Account("7", 900000));

        Bank bank = new Bank(accounts);

        Random random = new Random();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(() -> {
              bank.transfer(String.valueOf(random.nextInt(8)),
                      String.valueOf(random.nextInt(8)), random.nextInt(70000));

            }));
        }

        threads.forEach(Thread::start);
    }
}
