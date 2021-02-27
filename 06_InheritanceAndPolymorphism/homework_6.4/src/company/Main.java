package company;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class Main {


    public static void main(String[] args) {
        final String TITLE_OF_COMPANY = "KMK";
        final int COEFFICIENT_STAFF_REDUCTION = 2;
        final int ALL_AMOUNT_OF_STAFF = 270;
        final int ALL_AMOUNT_OF_OPERATORS = 180;
        final int ALL_AMOUNT_OF_MANAGERS = 80;
        final int ALL_AMOUNT_OF_TOP_MANAGERS = 10;
        final int AMOUNT_TOP_SALARY_STAFF = 15;
        final int AMOUNT_LOWEST_SALARY_STAFF = 30;

        Company kmkCompany = new Company(TITLE_OF_COMPANY);

        ArrayList<Employee> operators = new ArrayList<>();
        ArrayList<Employee> managers = new ArrayList<>();

        for (int i = 0; i < ALL_AMOUNT_OF_STAFF; i++) {

            if (i < ALL_AMOUNT_OF_OPERATORS) {
                operators.add(new Operator(" ID " + i + ""));
            }
            if (i >= ALL_AMOUNT_OF_OPERATORS && i < (ALL_AMOUNT_OF_OPERATORS + ALL_AMOUNT_OF_MANAGERS)) {
                managers.add(new Manager(" ID " + i + ""));
            }
            if (i >= (ALL_AMOUNT_OF_STAFF - ALL_AMOUNT_OF_TOP_MANAGERS)) {
                kmkCompany.hair(new TopManager(" ID " + i + ""));
            }
        }
        kmkCompany.hairAll(operators);
        kmkCompany.hairAll(managers);

        if (kmkCompany.getSuccessStatus()) {
            System.out.println("Компания " + kmkCompany.getCompanyTitle() + " преуспевает. Доход " + Math.round(kmkCompany.getIncome()) + "\n");
        } else {
            System.out.println("Компания " + kmkCompany.getCompanyTitle() + " в упадке. Доход " + Math.round(kmkCompany.getIncome()) + "\n");
        }

        List<Employee> topStaff = kmkCompany.getTopSalaryStaff(AMOUNT_TOP_SALARY_STAFF);
        System.out.println("Самые высокооплачиваемые сотрудники: ");
        for (Employee staff : topStaff) {
            System.out.println(staff);
        }
        System.out.println("Самые низкооплачиваемые сотрудники: ");
        List<Employee> bottomStaff = kmkCompany.getLowestSalaryStaff(AMOUNT_LOWEST_SALARY_STAFF);

        for (Employee otherStaff : bottomStaff) {
            System.out.println(otherStaff);
        }
        System.out.print("\n");
        int startStaffCount = kmkCompany.getEmployeeCount();
        int endStaffCount = kmkCompany.getEmployeeCount() / COEFFICIENT_STAFF_REDUCTION;
        System.out.println("Теперь уволим половину сотрудников.");
        for (int j = startStaffCount; j > endStaffCount; j--) {
            kmkCompany.fair(kmkCompany.getAllStaff().get(ThreadLocalRandom.current().nextInt(0, j - 1)));  //увольняем случайного сотрудника из списка
        }
        System.out.println("Осталось работать сотрудников: " + kmkCompany.getEmployeeCount() + "\n");

        if (kmkCompany.getSuccessStatus()) {
            System.out.println("Компания " + kmkCompany.getCompanyTitle() + " преуспевает. Доход " + Math.round(kmkCompany.getIncome()));
        } else {
            System.out.println("Компания " + kmkCompany.getCompanyTitle() + " в упадке. Доход " + Math.round(kmkCompany.getIncome()));
        }

        List<Employee> newTopStaff = kmkCompany.getTopSalaryStaff(AMOUNT_TOP_SALARY_STAFF);
        System.out.println("Самые высокооплачиваемые сотрудники: ");
        for (Employee staff : newTopStaff) {
            System.out.println(staff);
        }
        System.out.println("Самые низкооплачиваемые сотрудники: ");
        List<Employee> newBottomStaff = kmkCompany.getLowestSalaryStaff(AMOUNT_LOWEST_SALARY_STAFF);

        for (Employee otherStaff : newBottomStaff) {
            System.out.println(otherStaff);
        }
    }

}
