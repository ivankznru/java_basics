package company;

import java.util.concurrent.ThreadLocalRandom;

public class Manager implements Employee {
    private final double BASE_SALARY_MANAGE = 30000;
    private final double BONUS_COFFICIENT = 0.05;
    private final double MIN_AMOUNT_OF_EARN_MONEY = 115000;
    private final double MAX_AMOUNT_OF_EARN_MONEY = 140000;
    private String name;
    private double salary;
    private double profit;
    private double bonus;

    public Manager(String name) {
        this.name = name;
        this.profit = 0;
        this.bonus = 0;
        this.salary = 0;
    }

    @Override
    public void setSalary() {
        this.profit = ThreadLocalRandom.current().nextDouble(MIN_AMOUNT_OF_EARN_MONEY, MAX_AMOUNT_OF_EARN_MONEY);
        this.bonus = profit * BONUS_COFFICIENT;
        this.salary = BASE_SALARY_MANAGE + bonus;
    }

    public void setZeroSalary() {
        this.profit = 0;
        this.bonus = 0;
        this.salary = 0;
    }

    @Override
    public Double getMonthSalary() {
        return this.salary;
    }

    public double getProfit() {
        return this.profit;
    }

    @Override
    public String toString() {
        return "Менеджер " + this.name + " - " + Math.round(this.getMonthSalary());
    }
}
