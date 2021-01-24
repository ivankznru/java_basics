public class Main {

    public static void main(String[] args) {


    }


    public static int calculateSalarySum(String text) {
        int calculateSalarySum = 0;
        String[] sentences = text.split("\\,");
        for (int i = 0; i < sentences.length; i++) {
            String salary = sentences[i].replaceAll("[^0-9]", " ").trim();
            if (!salary.equals("")) {
                calculateSalarySum = calculateSalarySum + Integer.parseInt(salary);
            } else {
                calculateSalarySum = 0;
            }
        }
        return calculateSalarySum;
    }

}