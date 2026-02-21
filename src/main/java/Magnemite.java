import java.util.Scanner;

public class Magnemite {

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Magnemite");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner in = new Scanner(System.in);
        String line;

        Task[] tasks = new Task[100];
        int counter = 0;
        int num;

        do {
            line = in.nextLine();

            if (line.startsWith("list")) {

                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. ", i + 1);
                    System.out.println(tasks[i]);
                }
                System.out.println("____________________________________________________________");

            } else if (line.startsWith("mark")) {
                String[] parts = line.split(" ");
                num = Integer.parseInt(parts[1]);
                tasks[num - 1].isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[num - 1]);

            } else if (line.startsWith("unmark")) {
                String[] parts = line.split(" ");
                num = Integer.parseInt(parts[1]);
                tasks[num - 1].isDone = false;
                System.out.println("Ok! I've marked this task as not yet done:");
                System.out.println(tasks[num - 1]);

            } else if (line.startsWith("todo")) {

                String taskDesc = line.substring(5);
                tasks[counter] = new Todo(taskDesc);

                System.out.println("____________________________________________________________");
                System.out.println("Added a todo:");
                System.out.println(tasks[counter]);
                counter++;
                System.out.println(" Now you have " + counter + " tasks in the list.");
                System.out.println("____________________________________________________________");

            } else if (line.startsWith("deadline")) {

                String taskDesc = line.substring(9);
                String[] parts = taskDesc.split(" /by ");
                String description = parts[0];
                String by = parts[1];

                tasks[counter] = new Deadline(description, by);

                System.out.println("____________________________________________________________");
                System.out.println("Added a deadline:");
                System.out.println(tasks[counter]);
                counter++;
                System.out.println(" Now you have " + counter + " tasks in the list.");
                System.out.println("____________________________________________________________");

            } else if (line.startsWith("event")) {

                String taskDesc = line.substring(6);
                String[] parts = taskDesc.split(" /");
                String description = parts[0];
                String start = parts[1].substring(4);
                String end = parts[2].substring(2);

                tasks[counter] = new Event(description, start, end);
                System.out.println("____________________________________________________________");
                System.out.println("Added an event:");
                System.out.println(tasks[counter]);
                counter++;

                System.out.println(" Now you have " + counter + " tasks in the list.");
                System.out.println("____________________________________________________________");

            }

        } while (!line.startsWith("bye"));

        System.out.println("Bye. Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}
