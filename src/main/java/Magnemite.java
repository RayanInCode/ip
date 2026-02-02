import java.util.Scanner;

public class Magnemite {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Magnemite");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String line;
        String[] tasks = new String[100];
        int counter = 0;
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. ", i+1);
                    System.out.println(tasks[i]);
                }
                System.out.println("____________________________________________________________");
            }
            else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
                tasks[counter] = line;
                counter++;
            }
        } while (!line.equals("bye"));
        System.out.println("Bye. Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}
