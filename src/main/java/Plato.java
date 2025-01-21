import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Plato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("______________________________________");
        System.out.println("Greetings, I am Plato. Nice to meet you!");
        System.out.println("What would you like to do?");
        System.out.println("______________________________________");

        List<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            Task task = new Task(sc.nextLine());
            if (task.getDescription().equals("Farewell")) {
                System.out.println("______________________________________");
                System.out.println("Farewell. Hope to see you soon!");
                System.out.println("______________________________________");
                break;
            }
            else if (task.getDescription().equals("tasks?")) {
                System.out.println("______________________________________");
                System.out.println("Here is your list of tasks: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + "[" + tasks.get(i).getStatusIcon()
                            + "] " + tasks.get(i).getDescription());
                }
                System.out.println("______________________________________");
            }
            else if (task.getDescription().startsWith("mark")) {
                String[] parts = task.getDescription().split(" ");
                int id = Integer.parseInt(parts[1]) - 1;
                Task tobemarked = tasks.get(id);
                System.out.println("______________________________________");
                System.out.println("Well done. Marking this task as done.");
                tobemarked.markAsDone();
                System.out.println("[" + tobemarked.getStatusIcon() + "] " +
                        tobemarked.getDescription());
                System.out.println("______________________________________");
            }
            else if (task.getDescription().startsWith("unmark")) {
                String[] parts = task.getDescription().split(" ");
                int id = Integer.parseInt(parts[1]) - 1;
                Task tobeunmarked = tasks.get(id);
                System.out.println("______________________________________");
                System.out.println("Alright. Marking this task as not done.");
                tobeunmarked.markAsNotDone();
                System.out.println("[" + tobeunmarked.getStatusIcon() + "] " +
                        tobeunmarked.getDescription());
                System.out.println("______________________________________");
            }
            else {
                tasks.add(task);
                System.out.println("______________________________________");
                System.out.println("I have thus added this task: " + task.getDescription());
                System.out.println("______________________________________");
            }
        }
    }
}
