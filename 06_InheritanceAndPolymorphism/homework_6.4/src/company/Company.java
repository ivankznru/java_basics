package company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Company {


    private static final double MIN_GOAL_INCOME = 10000000;
    private ArrayList<Employee> allStaffOfCompany = new ArrayList<>();
    private String companyTitle;


    public Company(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public void hair(Employee staff) {
        allStaffOfCompany.add(staff);
        if (staff instanceof TopManager) {
            ((TopManager) staff).setOwnCompany(this);
        }
        staff.setSalary();
        if (staff instanceof Manager) {
            for (Employee employee : allStaffOfCompany) {
                if (employee instanceof TopManager)
                    ((TopManager) employee).recalculationBonus(this.getIncome());
            }
        }
    }

    public void hairAll(ArrayList<Employee> approvedStaff) {
        for (Employee staff : approvedStaff) {
            this.hair(staff);
        }
    }

    public void fair(Employee staff) {
        if (allStaffOfCompany.remove(staff)) {
            staff.setZeroSalary();
        }
        if (staff instanceof Manager) {
            for (Employee employee : allStaffOfCompany) {
                if (employee instanceof TopManager)
                    ((TopManager) employee).recalculationBonus(this.getIncome());
            }
        }
    }

    public double getIncome() {
        double income = 0;
        for (Employee staff : allStaffOfCompany) {
            if (staff instanceof Manager) {
                income = income + (((Manager) staff).getProfit());
            }
        }
        return income;
    }

    public boolean getSuccessStatus() {              // отдельный метод, чтобы сильно не загромождать код. Используется для проверки бонуса топ-менеджерам
        return this.getIncome() >= MIN_GOAL_INCOME;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public int getEmployeeCount() {               // чтобы проверять, сколько ещё уволить))
        return allStaffOfCompany.size();
    }

    public List<Employee> getAllStaff() {
        return allStaffOfCompany;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        Collections.sort(allStaffOfCompany, Collections.<Employee>reverseOrder());
        return allStaffOfCompany.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        Collections.sort(allStaffOfCompany);
        return allStaffOfCompany.subList(0, count);
    }

    public static double getMinGoalIncome() {
        return MIN_GOAL_INCOME;
    }
}






