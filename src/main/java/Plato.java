import java.util.Scanner;

public class Plato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("______________________________________");
        System.out.println("Greetings, I am Plato. Nice to meet you!");
        System.out.println("What would you like to do?");
        System.out.println("______________________________________");

        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            System.out.println("______________________________________");
            System.out.println("     " + userInput);
            System.out.println("______________________________________");
            if (userInput.equals("Farewell")) {
                System.out.println("Farewell. Hope to see you soon!");
                break;
            }
        }
    }
}
