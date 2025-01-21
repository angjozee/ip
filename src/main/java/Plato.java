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

        List<String> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            if (userInput.equals("Farewell")) {
                System.out.println("______________________________________");
                System.out.println("Farewell. Hope to see you soon!");
                System.out.println("______________________________________");
                break;
            }
            if (userInput.equals("tasks?")) {
                System.out.println("______________________________________");
                System.out.println("Here is your list of tasks: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("______________________________________");
            }
            else {
                tasks.add(userInput);
                System.out.println("______________________________________");
                System.out.println("I have thus added this task: " + userInput);
                System.out.println("______________________________________");
            }
        }
    }
}
