import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //    Collections.sort(staff, new Comparator<Employee>() {
        //       @Override
        //      public int compare(Employee o2, Employee o1) {
        //           int diff = o2.getSalary() - o1.getSalary();
        //           if (diff == 0) {
        //                          return o2.getName().compareTo(o1.getName());
        //                      } else {
        //                          return diff;
        //                        }
        //       }
        //     });

        staff.sort((e2,e1) -> {
            int diff = e2.getSalary() - e1.getSalary();
            if (diff == 0) {
                return e2.getName().compareTo(e1.getName());
            } else {
                return diff;
            }
        });

        for (Employee e :
                staff) {
            System.out.println(e);
        }
    }
}