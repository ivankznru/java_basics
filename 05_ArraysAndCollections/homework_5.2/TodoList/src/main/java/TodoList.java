import java.util.ArrayList;

public class TodoList {
ArrayList<String>todoList= new ArrayList<>();

        public void add(String todo) {
            todoList.add(todo);
            System.out.println("Добавлено дело: "+ todo );

        }

        public void add(int index, String todo) {
            if  (todoList.size()+1==index) {
                todoList.add(index-1, todo);
                System.out.println("Добавлено дело: "+ todo );
            }
            if  (todoList.size()>index) {
                todoList.add(index, todo);}
                System.out.println("Добавлено дело: "+ todo );
        }

        public void edit(String todo, int index) {
            if  (todoList.size()>index) {
                System.out.println( " Дело : "+ todoList.get(index) + ": заменено на : " + todo );
                todoList.set(index,todo);

            }
        }

        public void delete(int index) {

            if  (todoList.size() > index){
                System.out.println ("Дело: "+todoList.get(index) + ":удалено");
                todoList.remove(index);

            }
            else{System.out.println("Дело с таким номером не существует");}

        }

        public ArrayList<String> getTodos() {
for (int i =0 ; i< todoList.size();i++ ){
    System.out.println(i+" - "+ todoList.get(i));
}
   return  todoList;
        }
    }