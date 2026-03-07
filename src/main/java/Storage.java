import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Path path = Paths.get(filePath);

            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                Task task;

                switch (type) {
                case "T":
                    task = new Todo(desc);
                    break;
                case "D":
                    task = new Deadline(desc, parts[3]);
                    break;
                case "E":
                    task = new Event(desc, parts[3], parts[4]);
                    break;
                default:
                    continue;
                }

                if (isDone) {
                    task.mark();
                }

                tasks.add(task);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error loading file.");
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Task t : tasks) {
                writer.write(convertToFileFormat(t));
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

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