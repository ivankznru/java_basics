package company;


public interface Employee extends Comparable<Employee> {

    Double getMonthSalary();

    void setSalary();

    void setZeroSalary();

    @Override
    default int compareTo(Employee employee) {
        return getMonthSalary().compareTo(employee.getMonthSalary());
    }


}











