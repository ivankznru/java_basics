package company;


public class TopManager implements Employee {
    private final double BASE_SALARY_TOP_MANAGE = 100000;
    private final double BONUS_COFFICIENT = 1.5;
    private String name;
    private double baseSalary;
    private double bonus;
    private Company ownCompany;

    public TopManager(String name) {
        this.name = name;
        this.baseSalary = 0;
        this.bonus = 0;
    }

    @Override
    public void setSalary() {
        this.baseSalary = BASE_SALARY_TOP_MANAGE;
        this.recalculationBonus(this.ownCompany.getIncome());
    }

    public void recalculationBonus(double income) {
        if (income >= Company.getMinGoalIncome()) {
            this.bonus = this.baseSalary * BONUS_COFFICIENT;
        } else this.bonus = 0;
    }

    @Override
    public void setZeroSalary() {
        this.baseSalary = 0;
        this.bonus = 0;
    }

    public void setOwnCompany(Company company) {
        this.ownCompany = company;
    }

    @Override
    public Double getMonthSalary() {
        return this.baseSalary + this.bonus;
    }

    @Override
    public String toString() {
        return "Топ-менеджер " + this.name + " - " + Math.round (this.getMonthSalary());
    }
}
