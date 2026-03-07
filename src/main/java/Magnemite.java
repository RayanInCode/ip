import java.util.ArrayList;
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
        int num;

        ArrayList<Task> tasks = new ArrayList<>();

        do {

            line = in.nextLine();

            try {

                if (line.equals("bye")) {
                    break;
                }

                else if (line.equals("list")) {

                    ui.line();

                    if (tasks.isEmpty()) {
                        System.out.println("Your task list is empty.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
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

                    if (num <= 0 || num > tasks.size()) {
                        throw new DukeException("That task number does not exist.");
                    }

                    tasks.get(num - 1).mark();

                    ui.line();
                    System.out.println("Great! I've marked this task as done:");
                    System.out.println("  " + tasks.get(num - 1));
                    ui.line();
                }

                else if (line.startsWith("unmark")) {

                    String[] parts = line.split(" ");

                    if (parts.length < 2) {
                        throw new DukeException("Please specify the task number to unmark.");
                    }

                    num = Integer.parseInt(parts[1]);

                    if (num <= 0 || num > tasks.size()) {
                        throw new DukeException("That task number does not exist.");
                    }

                    tasks.get(num - 1).unmark();

                    ui.line();
                    System.out.println("Alright! I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(num - 1));
                    ui.line();
                }

                else if (line.startsWith("delete")) {

                    String[] parts = line.split(" ");

                    if (parts.length < 2) {
                        throw new DukeException("Please specify the task number to delete.");
                    }

                    num = Integer.parseInt(parts[1]);

                    if (num <= 0 || num > tasks.size()) {
                        throw new DukeException("That task number does not exist.");
                    }

                    Task removed = tasks.remove(num - 1);

                    ui.line();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    ui.line();
                }

                else if (line.startsWith("todo")) {

                    if (line.length() <= TODO_OFFSET) {
                        throw new DukeException("A todo must have a description.");
                    }

                    String taskDesc = line.substring(TODO_OFFSET).trim();

                    tasks.add(new Todo(taskDesc));

                    ui.line();
                    System.out.println("I've added this todo:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    ui.showTaskCount(tasks.size());
                    ui.line();
                }

                else if (line.startsWith("deadline")) {

                    if (!line.contains(" /by ")) {
                        throw new DukeException("Deadline format: deadline <description> /by <date>");
                    }

                    String taskDesc = line.substring(DEADLINE_OFFSET);
                    String[] parts = taskDesc.split(" /by ");

                    tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));

                    ui.line();
                    System.out.println("I've added this deadline:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    ui.showTaskCount(tasks.size());
                    ui.line();
                }

                else if (line.startsWith("event")) {

                    if (!line.contains(" /from ") || !line.contains(" /to ")) {
                        throw new DukeException("Event format: event <description> /from <start> /to <end>");
                    }

                    String taskDesc = line.substring(EVENT_OFFSET);
                    String[] parts = taskDesc.split(" /from | /to ");

                    tasks.add(new Event(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim()));

                    ui.line();
                    System.out.println("I've added this event:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    ui.showTaskCount(tasks.size());
                    ui.line();
                }

                else {
                    throw new DukeException("I’m not sure what that command means.");
                }

            } catch (DukeException e) {

                ui.line();
                System.out.println(e.getMessage());
                ui.line();

            } catch (NumberFormatException e) {

                ui.line();
                System.out.println("Task number must be a valid integer.");
                ui.line();
            }

        } while (true);

        System.out.println("Bye. Hope to see you again!");
        ui.line();
    }
}