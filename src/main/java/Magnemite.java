import java.util.Scanner;

public class Magnemite {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Magnemite");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String line;
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");
        } while (!line.equals("bye"));
        System.out.println("Bye. Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}
