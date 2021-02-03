import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            input.trim();
            if (input.matches("^ADD\\s[^0-9]*") ) {
                input.replaceAll("ADD", "");
                todoList.add(input.replaceAll("ADD", "").trim());

            }
            if (input.matches("^ADD\\s[0-9].*")) {
              int index = Integer.parseInt(input.replaceAll("[^0-9]", "").trim());
                todoList.add(index,input.replaceAll("^ADD\\s[0-9]", "").trim());

            }
            if (input.matches("LIST")) {
                todoList.getTodos();
            }
            if (input.matches("^EDIT\\s[0-9].*")) {
            int index = Integer.parseInt(input.replaceAll("[^0-9]", "").trim());
            todoList.edit(input.replaceAll("^EDIT\\s[0-9]", ""), index);

             }
             if (input.matches("^DELETE\\s[0-9]+")) {
             int index = Integer.parseInt(  input.replaceAll("[^0-9]", "").trim());
             todoList.delete(index);
                }
            }
        }
    }


