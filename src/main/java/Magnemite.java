import java.util.Scanner;

public class Magnemite {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
    }

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Magnemite");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String line;
        Task[] tasks = new Task[100];
        int counter = 0;
        int num;
        String command;

        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] parts = line.split(" ");
            command = parts[0];


            if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. ", i+1);
                    System.out.println("[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
                System.out.println("____________________________________________________________");
            } else if (command.equals("mark")) {
                num = Integer.parseInt(parts[1]);
                tasks[num-1].isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[num-1].getStatusIcon() + "] " + tasks[num-1].description);
            } else if (command.equals("unmark")) {
                num = Integer.parseInt(parts[1]);
                tasks[num-1].isDone = false;
                System.out.println("Ok! I've marked this task as not yet done:");
                System.out.println("[" + tasks[num-1].getStatusIcon() + "] " + tasks[num-1].description);
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
                tasks[counter] = new Task(line);
                counter++;
            }
        } while (!line.equals("bye"));
        System.out.println("Bye. Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}
