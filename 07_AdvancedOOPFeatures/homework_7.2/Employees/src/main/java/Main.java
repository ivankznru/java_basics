import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        //   e  это сокращенная форма от employee
     final Employee[] highSalaryEmployee = {null};
        staff.stream()
                .filter(e -> getYear(e.getWorkStart()) == year)
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(e -> highSalaryEmployee[0] =e);

        return highSalaryEmployee[0];

    }

    private static int getYear(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
    }
}