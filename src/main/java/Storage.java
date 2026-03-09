import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

/*
 * Storage class handles reading tasks from the file
 * and saving tasks back to the file.
 */
public class Storage {

    // Path of the file used to store tasks
    private final String filePath;

    // Constructor that initializes the storage file path
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /*
     * Loads tasks from the file and converts them
     * into Task objects stored in an ArrayList.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Path path = Paths.get(filePath);

            // Create file and directory if they do not exist
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            // Read file line by line
            while ((line = reader.readLine()) != null) {

                // Split stored task format
                String[] parts = line.split(" \\| ");

                // Skip invalid lines
                if (parts.length < 3) {
                    continue;
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                Task task = null;

                try {

                    // Determine task type and create appropriate object
                    switch (type) {

                    case "T":
                        task = new Todo(desc);
                        break;

                    case "D":
                        if (parts.length >= 4) {
                            task = new Deadline(desc, parts[3]);
                        }
                        break;

                    case "E":
                        if (parts.length >= 5) {
                            task = new Event(desc, parts[3], parts[4]);
                        }
                        break;

                    default:
                        continue;
                    }

                } catch (DukeException e) {
                    // Skip tasks with invalid data
                    System.out.println("Skipping invalid task in file: " + e.getMessage());
                    continue;
                }

                // Mark task as done if stored status indicates completion
                if (task != null && isDone) {
                    task.mark();
                }

                // Add valid task to the list
                if (task != null) {
                    tasks.add(task);
                }
            }

            reader.close();

        } catch (IOException e) {
            // Handle file reading errors
            System.out.println("Error loading file.");
        }

        return tasks;
    }


    // Saves the current list of tasks into the file.
    public void save(ArrayList<Task> tasks) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // Convert each task to file format and write it
            for (Task t : tasks) {
                writer.write(convertToFileFormat(t));
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            // Handle file writing errors
            System.out.println("Error saving file.");
        }
    }

    /*
     * Converts a Task object into the format
     * used for storing tasks in the file.
     */
    private String convertToFileFormat(Task task) {

        String status = task.isDone ? "1" : "0";

        if (task instanceof Todo) {
            return "T | " + status + " | " + task.description;
        }

        else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + status + " | " + d.description + " | " + d.by;
        }

        else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + status + " | " + e.description + " | " + e.from + " | " + e.to;
        }

        return "";
    }
}