package mouse.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mouse.MouseException;
import mouse.task.Deadline;
import mouse.task.Event;
import mouse.task.Priority;
import mouse.task.Task;
import mouse.task.TaskList;
import mouse.task.ToDo;

/**
 * Loads and saves tasks to a text file.
 */
public class Storage {
    private static final String FIELD_SEPARATOR = " \\| ";

    private final Path filePath;

    /**
     * Creates storage for the given file path.
     *
     * @param filePath Path to the save file.
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Returns tasks from disk.
     * A missing file yields an empty list. Invalid lines are skipped.
     *
     * @return Loaded tasks.
     */
    public TaskList load() {
        if (!Files.exists(filePath)) {
            return new TaskList();
        }
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            TaskList tasks = new TaskList();
            for (String line : lines) {
                Task task = decode(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (IOException exception) {
            return new TaskList();
        }
    }

    /**
     * Writes {@code tasks} to disk, creating parent folders if needed.
     *
     * @param tasks Tasks to save.
     * @throws MouseException If the file cannot be written.
     */
    public void save(TaskList tasks) throws MouseException {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            List<String> lines = new ArrayList<>();
            for (Task task : tasks.asList()) {
                lines.add(task.encode());
            }
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new MouseException("Could not save tasks to " + filePath);
        }
    }

    private static Task decode(String line) {
        if (line == null || line.isBlank()) {
            return null;
        }
        String[] parts = line.split(FIELD_SEPARATOR, -1);
        if (parts.length < 4) {
            return null;
        }
        String type = parts[0];
        String doneFlag = parts[1];
        String priorityRaw = parts[2];
        try {
            Task task = decodeTask(type, parts);
            if (task == null) {
                return null;
            }
            if ("1".equals(doneFlag)) {
                task.markAsDone();
            }
            try {
                task.setPriority(Priority.fromString(priorityRaw));
            } catch (MouseException exception) {
                task.setPriority(Priority.NONE);
            }
            return task;
        } catch (RuntimeException exception) {
            return null;
        }
    }

    private static Task decodeTask(String type, String[] parts) {
        switch (type) {
        case "T":
            return new ToDo(join(parts, 3, parts.length));
        case "D":
            if (parts.length < 5) {
                return null;
            }
            return new Deadline(join(parts, 3, parts.length - 1), parts[parts.length - 1]);
        case "E":
            if (parts.length < 6) {
                return null;
            }
            return new Event(join(parts, 3, parts.length - 2),
                    parts[parts.length - 2], parts[parts.length - 1]);
        default:
            return null;
        }
    }

    private static String join(String[] parts, int from, int to) {
        return String.join(" | ", Arrays.copyOfRange(parts, from, to));
    }
}
