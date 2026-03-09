import java.util.ArrayList;
import java.util.Scanner;

/*
 * Main entry point for the Magnemite task manager application.
 * This class runs the command line interface, processes user input,
 * and performs task operations such as adding, deleting, marking,
 * unmarking, and searching tasks.
 */
public class Magnemite {

    /*
     * Starts the Magnemite application.
     * Initializes the UI, loads tasks from storage, and continuously
     * reads user input until the "bye" command is given.
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        ArrayList<Task> tasks = storage.load();

        ui.line();
        System.out.println("Hello! I'm Magnemite");
        System.out.println("What can I do for you?");
        ui.line();

        Scanner in = new Scanner(System.in);

        // Main command loop
        while (true) {

            String line = in.nextLine();

            try {

                // Parse the command keyword from user input
                String command = Parser.getCommand(line);

                // Exit command
                if (command.equals("bye")) {
                    break;
                }

                 // Displays all tasks currently stored in the task list.
                else if (command.equals("list")) {

                    ui.line();

                    if (tasks.isEmpty()) {
                        System.out.println("Your task list is empty.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                        }
                    }

                    ui.line();
                }

                 //Marks a specific task as completed.
                else if (command.equals("mark")) {

                    int num = Parser.getTaskNumber(line);

                    if (num <= 0 || num > tasks.size()) {
                        throw new DukeException("That task number does not exist.");
                    }

                    tasks.get(num - 1).mark();
                    storage.save(tasks);

                    ui.line();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(num - 1));
                    ui.line();
                }

                 //Marks a specific task as not completed.
                else if (command.equals("unmark")) {

                    int num = Parser.getTaskNumber(line);

                    if (num <= 0 || num > tasks.size()) {
                        throw new DukeException("That task number does not exist.");
                    }

                    tasks.get(num - 1).unmark();
                    storage.save(tasks);

                    ui.line();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(num - 1));
                    ui.line();
                }

                // Deletes a task from the task list.
                else if (command.equals("delete")) {

                    int num = Parser.getTaskNumber(line);

                    if (num <= 0 || num > tasks.size()) {
                        throw new DukeException("That task number does not exist.");
                    }

                    Task removed = tasks.remove(num - 1);
                    storage.save(tasks);

                    ui.line();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    ui.line();
                }

                 // Adds a new Todo task to the list.
                else if (command.equals("todo")) {

                    String desc = Parser.parseTodo(line);

                    Task task = new Todo(desc);
                    tasks.add(task);
                    storage.save(tasks);

                    ui.line();
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task);
                    ui.showTaskCount(tasks.size());
                    ui.line();
                }

                // Adds a deadline task with a due date.
                else if (command.equals("deadline")) {

                    String[] parts = Parser.parseDeadline(line);

                    Task task = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(task);
                    storage.save(tasks);

                    ui.line();
                    System.out.println("Got it. I've added this deadline:");
                    System.out.println("  " + task);
                    ui.showTaskCount(tasks.size());
                    ui.line();
                }

                /*
                 * Adds an event task with a start and end time.
                 */
                else if (command.equals("event")) {

                    String[] parts = Parser.parseEvent(line);

                    Task task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    tasks.add(task);
                    storage.save(tasks);

                    ui.line();
                    System.out.println("Got it. I've added this event:");
                    System.out.println("  " + task);
                    ui.showTaskCount(tasks.size());
                    ui.line();
                }

                // Finds and displays tasks that contain the specified keyword.
                else if (command.equals("find")) {

                    String keyword = Parser.parseFind(line);

                    ui.line();
                    System.out.println("Here are the matching tasks in your list:");

                    int count = 1;

                    for (Task t : tasks) {
                        if (t.description.toLowerCase().contains(keyword.toLowerCase())) {
                            System.out.println(count + "." + t);
                            count++;
                        }
                    }

                    if (count == 1) {
                        System.out.println("No matching tasks found.");
                    }

                    ui.line();
                }

                // Handles unknown commands entered by the user
                else {
                    throw new DukeException("I'm sorry, but I don't know what that means.");
                }

            } catch (DukeException e) {

                ui.line();
                System.out.println(e.getMessage());
                ui.line();
            }
        }

        // Exit message
        System.out.println("Bye. Hope to see you again!");
        ui.line();
    }
}