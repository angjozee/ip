import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Plato {
    public static void main(String[] args) throws PlatoException {
        Scanner sc = new Scanner(System.in);
        System.out.println("______________________________________");
        System.out.println("Greetings, I am Plato. Nice to meet you!");
        System.out.println("What would you like to do?");
        System.out.println("______________________________________");


        List<Task> tasks = new ArrayList<>();


        while (sc.hasNext()) {
            try {
                String userInput = sc.nextLine();
                if (userInput.startsWith("deadline")) {
                    String description = userInput.substring(9).trim();
                    String[] split = description.split("/by ");
                    if (split.length != 2 || split[0].trim().isEmpty()) {
                        throw new PlatoException("Invalid format for deadline. " +
                                "Use the format: 'deadline <description> /by <date>'.");
                    }
                    Task task = new Deadline(split[0], split[1]);
                    tasks.add(task);
                    System.out.println("Alright. Added your task " + task.getDescription() + "to the list. " +
                            "Do it by " + split[1] + ".");
                    System.out.println("You now have " + tasks.size() + " tasks.");
                    System.out.println("______________________________________");
                    continue;
                }
                if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 5) {
                        throw new PlatoException("The description of a todo shall not be empty. " +
                                "Do provide a task description.");
                    }
                    String description = userInput.substring(5);
                    Task task = new ToDo(description);
                    tasks.add(task);
                    System.out.println("Alright. Added your task " + task.getDescription() + " to the list.");
                    System.out.println("You now have " + tasks.size() + " tasks.");
                    System.out.println("______________________________________");
                    continue;
                }
                if (userInput.startsWith("event")) {
                    String description = userInput.substring(6);
                    String[] split = description.split("/from|/to");
                    if (split.length != 3 || split[0].trim().isEmpty()) {
                        throw new PlatoException("Invalid format for event. " +
                                "Use the format: 'event <description> /from <date> " +
                                "/to <date>'.");
                    }
                    Task task = new Event(split[0], split[1].trim(), split[2].trim());
                    tasks.add(task);
                    System.out.println("Alright. Added your task " + task.getDescription() + "to the list."
                        + " Do it from" + split[1] + "to" + split[2] + ".");
                    System.out.println("You now have " + tasks.size() + " tasks.");
                    System.out.println("______________________________________");
                    continue;
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
                    if (parts.length != 2) {
                        throw new PlatoException("Invalid format for mark. Use: 'mark <task_number>'.");
                    }
                    int id = Integer.parseInt(parts[1]) - 1;
                    if (id < 0 || id >= tasks.size()) { // Validate the index range
                        throw new PlatoException("Task number out of range. " +
                                "Please provide a valid task number between 1 and " + tasks.size() + ".");
                    }
                    Task tobemarked = tasks.get(id);
                    System.out.println("______________________________________");
                    System.out.println("Well done. Marking this task as done.");
                    tobemarked.markAsDone();
                    System.out.println(tobemarked);
                    System.out.println("______________________________________");
                }
                else if (userInput.startsWith("unmark")) {
                    String[] parts = userInput.split(" ");
                    if (parts.length != 2) {
                        throw new PlatoException("Invalid format for unmark. " +
                                "Use: 'unmark <task_number>'.");
                    }
                    int id = Integer.parseInt(parts[1]) - 1;
                    if (id < 0 || id >= tasks.size()) { // Validate the index range
                        throw new PlatoException("Task number out of range. " +
                                "Please provide a valid task number between 1 and " + tasks.size() + ".");
                    }
                    Task tobeunmarked = tasks.get(id);
                    System.out.println("______________________________________");
                    System.out.println("Alright. Marking this task as not done.");
                    tobeunmarked.markAsNotDone();
                    System.out.println(tobeunmarked);
                    System.out.println("______________________________________");
                } else if (userInput.startsWith("delete")) {
                    String[] parts = userInput.split(" ");
                    if (parts.length != 2) {
                        throw new PlatoException("Invalid format for delete. " +
                                "Use: 'delete <task_number>'.");
                    }
                    int id = Integer.parseInt(parts[1]) - 1;
                    if (id < 0 || id >= tasks.size()) {
                        throw new PlatoException("Task number out of range. " +
                                "Please provide a valid task number between 1 and " + tasks.size() + ".");
                    }
                    Task tobedeleted = tasks.get(id);
                    System.out.println("______________________________________");
                    System.out.println("Congratulations. Deleting this task: " + tobedeleted.getDescription());
                    tasks.remove(id);
                    System.out.println("You now have " + tasks.size() + " tasks.");
                    System.out.println("______________________________________");
                    }
                else {
                    throw new PlatoException("Unknown command. Please try again with a valid command.");
                }
            } catch (PlatoException e) {
                System.out.println("______________________________________");
                System.out.println("Error: " + e.getMessage());
                System.out.println("______________________________________");
            } catch (NumberFormatException e) {
                System.out.println("______________________________________");
                System.out.println("Error: Task number must be a valid integer.");
                System.out.println("______________________________________");
            }
        }
    }
}
