import java.util.ArrayList;
import java.util.Scanner;

public class Magnemite {

    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        ArrayList<Task> tasks = storage.load();

        ui.line();
        System.out.println("Hello! I'm Magnemite");
        System.out.println("What can I do for you?");
        ui.line();

        Scanner in = new Scanner(System.in);
        String line;

        do {

            line = in.nextLine();

            try {

                String command = Parser.getCommand(line);

                if (command.equals("bye")) {
                    break;
                }

                else if (command.equals("list")) {

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

                else if (command.equals("mark")) {

                    int num = Parser.getTaskNumber(line);

                    if (num <= 0 || num > tasks.size()) {
                        throw new DukeException("That task number does not exist.");
                    }

                    tasks.get(num - 1).mark();
                    storage.save(tasks);

                    ui.line();
                    System.out.println("Great! I've marked this task as done:");
                    System.out.println("  " + tasks.get(num - 1));
                    ui.line();
                }

                else if (command.equals("unmark")) {

                    int num = Parser.getTaskNumber(line);

                    if (num <= 0 || num > tasks.size()) {
                        throw new DukeException("That task number does not exist.");
                    }

                    tasks.get(num - 1).unmark();
                    storage.save(tasks);

                    ui.line();
                    System.out.println("Alright! I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(num - 1));
                    ui.line();
                }

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

                else if (command.equals("todo")) {

                    String desc = Parser.parseTodo(line);

                    tasks.add(new Todo(desc));
                    storage.save(tasks);

                    ui.line();
                    System.out.println("I've added this todo:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    ui.showTaskCount(tasks.size());
                    ui.line();
                }

                else if (command.equals("deadline")) {

                    String[] parts = Parser.parseDeadline(line);

                    tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                    storage.save(tasks);

                    ui.line();
                    System.out.println("I've added this deadline:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    ui.showTaskCount(tasks.size());
                    ui.line();
                }

                else if (command.equals("event")) {

                    String[] parts = Parser.parseEvent(line);

                    tasks.add(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                    storage.save(tasks);

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
            }

        } while (true);

        System.out.println("Bye. Hope to see you again!");
        ui.line();
    }
}

