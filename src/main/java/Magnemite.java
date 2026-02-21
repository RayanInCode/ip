import java.util.Scanner;

public class Magnemite {
    private static final int TODO_OFFSET = 5;
    private static final int DEADLINE_OFFSET = 9;
    private static final int EVENT_OFFSET = 6;

    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.line();
        System.out.println("Hello! I'm Magnemite");
        System.out.println("What can I do for you?");
        ui.line();

        Scanner in = new Scanner(System.in);
        String line;

        Task[] tasks = new Task[100];
        int counter = 0;
        int num;

        do {
            line = in.nextLine();
            if (line.startsWith("list")) {

                ui.line();
                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. ", i + 1);
                    System.out.println(tasks[i]);
                }
                ui.line();

            } else if (line.startsWith("mark")) {

                String[] parts = line.split(" ");
                num = Integer.parseInt(parts[1]);
                tasks[num - 1].mark();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[num - 1]);

            } else if (line.startsWith("unmark")) {

                String[] parts = line.split(" ");
                num = Integer.parseInt(parts[1]);
                tasks[num - 1].unmark();

                System.out.println("Ok! I've marked this task as not yet done:");
                System.out.println(tasks[num - 1]);

            } else if (line.startsWith("todo")) {

                String taskDesc = line.substring(TODO_OFFSET);
                tasks[counter] = new Todo(taskDesc);

                ui.line();
                System.out.println("Added a todo:");
                System.out.println(tasks[counter]);
                counter++;
                ui.showTaskCount(counter);
                ui.line();

            } else if (line.startsWith("deadline")) {

                String taskDesc = line.substring(DEADLINE_OFFSET);
                String[] parts = taskDesc.split(" /by ");
                String description = parts[0];
                String by = parts[1];
                tasks[counter] = new Deadline(description, by);

                ui.line();
                System.out.println("Added a deadline:");
                System.out.println(tasks[counter]);
                counter++;
                ui.showTaskCount(counter);
                ui.line();

            } else if (line.startsWith("event")) {

                String taskDesc = line.substring(EVENT_OFFSET);
                String[] parts = taskDesc.split(" /");
                String description = parts[0];
                String start = parts[1].substring(4);
                String end = parts[2].substring(2);
                tasks[counter] = new Event(description, start, end);

                ui.line();
                System.out.println("Added an event:");
                System.out.println(tasks[counter]);
                counter++;
                ui.showTaskCount(counter);
                ui.line();

            }

        } while (!line.startsWith("bye"));
        System.out.println("Bye. Hope to see you again!");
        ui.line();
    }
}
