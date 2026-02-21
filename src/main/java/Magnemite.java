import java.util.Scanner;

public class Magnemite {

    public static class Task {
        protected String description;
        protected boolean isDone;
        protected String tag; // T = todo, D = deadline, E = event

        public Task(String description, String tag) {
            this.description = description;
            this.tag = tag;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public String getTagIcon() {
            return tag;
        }
    }

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Magnemite");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner in = new Scanner(System.in);
        String line;
        String command;

        Task[] tasks = new Task[100];
        int counter = 0;
        int num;

        do {
            line = in.nextLine();
            String[] parts = line.split(" ");
            command = parts[0];

            if (command.equals("list")) {

                System.out.println("____________________________________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. ", i + 1);
                    System.out.println(
                            "[" + tasks[i].getTagIcon() + "] "
                                    + "[" + tasks[i].getStatusIcon() + "] "
                                    + tasks[i].description
                    );
                }
                System.out.println("____________________________________________________________");

            } else if (command.equals("mark")) {
                num = Integer.parseInt(parts[1]);
                tasks[num - 1].isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(
                        "[" + tasks[num - 1].getTagIcon() + "] "
                                + "[" + tasks[num - 1].getStatusIcon() + "] "
                                + tasks[num - 1].description
                );

            } else if (command.equals("unmark")) {

                num = Integer.parseInt(parts[1]);
                tasks[num - 1].isDone = false;
                System.out.println("Ok! I've marked this task as not yet done:");
                System.out.println(
                        "[" + tasks[num - 1].getTagIcon() + "] "
                                + "[" + tasks[num - 1].getStatusIcon() + "] "
                                + tasks[num - 1].description
                );

            } else if (command.equals("todo")) {

                String taskDesc = line.substring(5);
                tasks[counter] = new Task(taskDesc, "T");
                counter++;
                System.out.println("____________________________________________________________");
                System.out.println("Added a todo:");
                System.out.println("[T] [ ] " + taskDesc);
                System.out.println("____________________________________________________________");

            } else if (command.equals("deadline")) {

                String taskDesc = line.substring(9);
                tasks[counter] = new Task(taskDesc, "D");
                counter++;
                System.out.println("____________________________________________________________");
                System.out.println("Added a deadline:");
                System.out.println("[D] [ ] " + taskDesc);
                System.out.println("____________________________________________________________");

            } else if (command.equals("event")) {

                String taskDesc = line.substring(6);
                tasks[counter] = new Task(taskDesc, "E");
                counter++;
                System.out.println("____________________________________________________________");
                System.out.println("Added an event:");
                System.out.println("[E] [ ] " + taskDesc);
                System.out.println("____________________________________________________________");

            }

        } while (!line.equals("bye"));

        System.out.println("Bye. Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}
