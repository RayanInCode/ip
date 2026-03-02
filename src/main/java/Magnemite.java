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

            try {

                if (line.equals("bye")) {
                    break;
                }

                else if (line.equals("list")) {

                    ui.line();
                    if (counter == 0) {
                        System.out.println("Your task list is empty.");
                    } else {
                        for (int i = 0; i < counter; i++) {
                            System.out.printf("%d. %s\n", i + 1, tasks[i]);
                        }
                    }
                    ui.line();
                }

                else if (line.startsWith("mark")) {

                    String[] parts = line.split(" ");

                    if (parts.length < 2) {
                        throw new DukeException("Please specify the task number to mark.");
                    }

                    num = Integer.parseInt(parts[1]);

                    if (num <= 0 || num > counter) {
                        throw new DukeException("That task number does not exist.");
                    }

                    tasks[num - 1].mark();

                    ui.line();
                    System.out.println("Great! I've marked this task as done:");
                    System.out.println(tasks[num - 1]);
                    ui.line();
                }

                else if (line.startsWith("unmark")) {

                    String[] parts = line.split(" ");

                    if (parts.length < 2) {
                        throw new DukeException("Please specify the task number to unmark.");
                    }

                    num = Integer.parseInt(parts[1]);

                    if (num <= 0 || num > counter) {
                        throw new DukeException("That task number does not exist.");
                    }

                    tasks[num - 1].unmark();

                    ui.line();
                    System.out.println("Alright! I've marked this task as not done yet:");
                    System.out.println(tasks[num - 1]);
                    ui.line();
                }

                else if (line.startsWith("todo")) {

                    if (line.length() <= TODO_OFFSET) {
                        throw new DukeException("A todo must have a description.");
                    }

                    String taskDesc = line.substring(TODO_OFFSET).trim();

                    if (taskDesc.isEmpty()) {
                        throw new DukeException("You forgot to include a description for the todo.");
                    }

                    tasks[counter] = new Todo(taskDesc);

                    ui.line();
                    System.out.println("I've added this todo:");
                    System.out.println(tasks[counter]);
                    counter++;
                    ui.showTaskCount(counter);
                    ui.line();
                }

                else if (line.startsWith("deadline")) {

                    if (!line.contains(" /by ")) {
                        throw new DukeException("Deadline format: deadline <description> /by <date>");
                    }

                    String taskDesc = line.substring(DEADLINE_OFFSET);
                    String[] parts = taskDesc.split(" /by ");

                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new DukeException("Both description and deadline date must be provided.");
                    }

                    tasks[counter] = new Deadline(parts[0].trim(), parts[1].trim());

                    ui.line();
                    System.out.println("I've added this deadline:");
                    System.out.println(tasks[counter]);
                    counter++;
                    ui.showTaskCount(counter);
                    ui.line();
                }

                else if (line.startsWith("event")) {

                    if (!line.contains(" /from ") || !line.contains(" /to ")) {
                        throw new DukeException("Event format: event <description> /from <start> /to <end>");
                    }

                    String taskDesc = line.substring(EVENT_OFFSET);
                    String[] parts = taskDesc.split(" /from | /to ");

                    if (parts.length < 3 ||
                            parts[0].trim().isEmpty() ||
                            parts[1].trim().isEmpty() ||
                            parts[2].trim().isEmpty()) {

                        throw new DukeException("Event must include description, start time, and end time.");
                    }

                    tasks[counter] = new Event(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim());

                    ui.line();
                    System.out.println("I've added this event:");
                    System.out.println(tasks[counter]);
                    counter++;
                    ui.showTaskCount(counter);
                    ui.line();
                }

                else {
                    throw new DukeException("I’m not sure what that command means. Try 'list', 'todo', 'deadline', or 'event'.");
                }

            } catch (DukeException e) {

                ui.line();
                System.out.println(e.getMessage());
                ui.line();

            } catch (NumberFormatException e) {

                ui.line();
                System.out.println("Task number must be a valid integer.");
                ui.line();

            } catch (Exception e) {

                ui.line();
                System.out.println("Something unexpected happened. Please check your input format.");
                ui.line();
            }

        } while (true);

        System.out.println("Bye. Hope to see you again!");
        ui.line();
    }
}