import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskManager {
    private Scanner inScanner;
    private Map<Integer, Task> tasks;
    private LocalDate date = LocalDate.now();
    private int globalId = 0;

    private LocalDate getDate() {
        System.out.println("Complete date (d/MM/yyyy): ");
        while (true) {
            try {
                return LocalDate.parse(
                    inScanner.next(),
                    DateTimeFormatter.ofPattern("d/MM/yyyy")
                );
            } catch (Exception e){
                System.out.println("try again");
                continue;
            }
        }
    }

    private int getId() {
        printTasks(4);
        while (true) {
            System.out.println("Enter task id: ");
            int id = inScanner.nextInt();
            if (tasks.containsKey(id)) {
                return id;
            }
        }
    }

    private void newTask() { 
        System.out.println("Task name: ");
        String name = inScanner.next();

        System.out.println("Additional info: ");
        String info = inScanner.next();

        globalId++;
        tasks.put(globalId, new Task(globalId, name, info, LocalDate.now(), getDate()));
    }
    
    private void deleteTask(){
        System.out.println("Task to delete: ");
        int id = getId();
        if (id < tasks.size()) {
            tasks.remove(id);
        }
    }
    
    private void printTasks(int type) {
        for (Task task : tasks.values()) {
            if (
                type == 4 ||
                type == 5 && task.isDone() ||
                type == 6 && !task.isDone() ||
                type == 7 && task.getCompleteDate().equals(date) ||
                type == 8 && task.getCreationDate().equals(date)
            ) {
                System.out.println(task.getId() + " " + task.getName());
            }
        }
    }

    private void printTaskDetailed(Task task) {                
        System.out.println(
            "Name (1): " + task.getName() + "\n" +
            "Add. info (2): " + task.getInfo() + "\n" +
            "Creation date (3): " + task.getCreationDate() + "\n" +
            "Complete date (4): " + task.getCompleteDate() + "\n" +
            "Choose one of option above: "
        );
    }
    
    private void editTask() {
        System.out.println("Task to edit: ");

        int id = getId();
        Task task = tasks.get(id);
        int input = inScanner.nextInt();
        if (input == 1) {
            System.out.println("Name: ");
            task.setName(inScanner.next());
        } else if (input == 2) {
            System.out.println("Info: ");
            task.setInfo(inScanner.next());
        } else if (input == 3) {  
            System.out.println("Creation date: ");
            task.setCreationDate(getDate());
        } else if (input == 4) {
            System.out.println("Complete date: ");
            task.setCompleteDate(getDate());
        } else {
            return;
        }
    }


    private void getCommand() {
        while (true) {
            System.out.println("waiting for command...");
            int choice;
            try {
                choice = inScanner.nextInt();
            } catch (InputMismatchException e) {
                continue;
            }

            if (choice == 1) {
                newTask();
            } else if (choice == 2) {
                deleteTask();
            } else if (choice == 3) {
                editTask();
            } else if (4 <= choice && choice <= 6) {
                printTasks(choice);
            } else if (choice == 7 || choice == 8) {
                date = getDate();
                printTasks(choice);
            } else if (choice == 9) {
                printTaskDetailed(tasks.get(getId()));
            } else {
                break;
            }
        } 
    }


    public TaskManager(){
        this.tasks = new HashMap<>();
        this.inScanner = new Scanner(System.in);

        System.out.println(
            "1 - new\n" +
            "2 - delete\n" +
            "3 - edit\n" +
            "4 - print all\n" +
            "5 - print completed\n" +
            "6 - print incompleted\n" +
            "7 - print by complete date\n" +
            "8 - print by creation date\n" +
            "9 - print full description\n" +
            "Any other key to exit\n" +
            "Your choice: "
        );

        getCommand();
    }
}
