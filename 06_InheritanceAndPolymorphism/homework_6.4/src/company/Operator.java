package company;

public class Operator implements Employee {
    private final double BASE_SALARY_OPERATOR = 20000;
    private String name;
    private double salary;

    public Operator(String name) {

        this.salary = 0;
        this.name = name;
    }


    @Override
    public void setSalary() {
        this.salary = BASE_SALARY_OPERATOR;
    }

    @Override
    public void setZeroSalary() {
        this.salary = 0;
    }

    @Override
    public Double getMonthSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return "Оператор " + this.name + " - " + Math.round(this.getMonthSalary()) ;
    }
}
