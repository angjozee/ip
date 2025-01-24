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
            String userInput = sc.nextLine();
            if (userInput.startsWith("deadline")) {
                String description = userInput.substring(9);
                String[] split = description.split("/by ");
                Task task = new Deadline(split[0], split[1]);
                tasks.add(task);
                System.out.println("Alright. Added your task " + task.getDescription() + "to the list. " +
                        "Do it by " + split[1] + ".");
                System.out.println("You now have " + tasks.size() + " tasks.");
                System.out.println("______________________________________");
            }
            if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                Task task = new ToDo(description);
                tasks.add(task);
                System.out.println("Alright. Added your task " + task.getDescription() + " to the list.");
                System.out.println("You now have " + tasks.size() + " tasks.");
                System.out.println("______________________________________");
            }
            if (userInput.startsWith("event")) {
                String description = userInput.substring(6);
                String[] split = description.split("/from|/to");
                Task task = new Event(split[0], split[1].trim(), split[2].trim());
                tasks.add(task);
                System.out.println("Alright. Added your task " + task.getDescription() + "to the list."
                    + " Do it from" + split[1] + "to" + split[2] + ".");
                System.out.println("You now have " + tasks.size() + " tasks.");
                System.out.println("______________________________________");
            }

            if (userInput.equals("Farewell")) {
                System.out.println("______________________________________");
                System.out.println("Farewell. Hope to see you soon!");
                System.out.println("______________________________________");
                break;
            }
            else if (userInput.equals("tasks?")) {
                System.out.println("______________________________________");
                System.out.println("Here is your list of tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
                System.out.println("______________________________________");
            }
            else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ");
                int id = Integer.parseInt(parts[1]) - 1;
                Task tobemarked = tasks.get(id);
                System.out.println("______________________________________");
                System.out.println("Well done. Marking this task as done.");
                tobemarked.markAsDone();
                System.out.println(tobemarked);
                System.out.println("______________________________________");
            }
            else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ");
                int id = Integer.parseInt(parts[1]) - 1;
                Task tobeunmarked = tasks.get(id);
                System.out.println("______________________________________");
                System.out.println("Alright. Marking this task as not done.");
                tobeunmarked.markAsNotDone();
                System.out.println(tobeunmarked);
                System.out.println("______________________________________");
            }
        }
    }
}
