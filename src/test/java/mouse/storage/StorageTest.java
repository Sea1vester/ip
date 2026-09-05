package mouse.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import mouse.MouseException;
import mouse.task.Deadline;
import mouse.task.Priority;
import mouse.task.TaskList;
import mouse.task.ToDo;

/**
 * Tests for {@link Storage} load and save.
 */
public class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    public void load_missingFile_returnsEmptyList() {
        Storage storage = new Storage(tempDir.resolve("missing.txt").toString());
        assertEquals(0, storage.load().size());
    }

    @Test
    public void saveThenLoad_roundTrip_restoresTasks() throws MouseException {
        Path file = tempDir.resolve("mouse.txt");
        Storage storage = new Storage(file.toString());
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("read book");
        todo.markAsDone();
        todo.setPriority(Priority.HIGH);
        tasks.add(todo);
        tasks.add(new Deadline("return book", "2019-12-02"));
        storage.save(tasks);

        TaskList loaded = storage.load();
        assertEquals(2, loaded.size());
        assertEquals("read book", loaded.get(0).getDescription());
        assertTrue(loaded.get(0).isDone());
        assertEquals(Priority.HIGH, loaded.get(0).getPriority());
        assertEquals("[D][ ] return book (by: Dec 02 2019)", loaded.get(1).toString());
    }
}
