package mouse.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link TaskList} list mutations.
 */
public class TaskListTest {
    @Test
    public void add_oneTask_sizeIsOne() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));
        assertEquals(1, tasks.size());
    }

    @Test
    public void mark_validIndex_marksTaskDone() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));
        Task marked = tasks.mark(0);
        assertEquals("X", marked.getStatusIcon());
        assertEquals("X", tasks.get(0).getStatusIcon());
    }

    @Test
    public void mark_invalidIndex_throwsIndexOutOfBoundsException() {
        TaskList tasks = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.mark(0));
        tasks.add(new ToDo("read book"));
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.mark(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.mark(1));
    }

    @Test
    public void unmark_validIndex_marksTaskNotDone() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));
        tasks.mark(0);
        Task unmarked = tasks.unmark(0);
        assertEquals(" ", unmarked.getStatusIcon());
    }

    @Test
    public void unmark_invalidIndex_throwsIndexOutOfBoundsException() {
        TaskList tasks = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.unmark(0));
    }

    @Test
    public void delete_validIndex_removesTaskAndShrinksList() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("a"));
        tasks.add(new ToDo("b"));
        Task removed = tasks.delete(0);
        assertEquals("a", removed.getDescription());
        assertEquals(1, tasks.size());
        assertEquals("b", tasks.get(0).getDescription());
    }

    @Test
    public void delete_invalidIndex_throwsIndexOutOfBoundsException() {
        TaskList tasks = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.delete(0));
    }

    @Test
    public void delete_middleTask_reindexesRemainingTasks() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("a"));
        tasks.add(new ToDo("b"));
        tasks.add(new ToDo("c"));
        tasks.delete(1);
        assertEquals(2, tasks.size());
        assertEquals("a", tasks.get(0).getDescription());
        assertEquals("c", tasks.get(1).getDescription());
    }

    @Test
    public void setPriority_validIndex_updatesTaskPriority() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));
        Task updated = tasks.setPriority(0, Priority.HIGH);
        assertEquals(Priority.HIGH, updated.getPriority());
        assertEquals("[T][ ] read book (priority: high)", updated.toString());
    }

    @Test
    public void setPriority_invalidIndex_throwsIndexOutOfBoundsException() {
        TaskList tasks = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> tasks.setPriority(0, Priority.LOW));
    }

    @Test
    public void find_keywordInDescription_returnsMatchingTasksOnly() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("read book"));
        tasks.add(new ToDo("buy bread"));
        tasks.add(new ToDo("return book"));
        TaskList matches = tasks.find("book");
        assertEquals(2, matches.size());
        assertEquals("read book", matches.get(0).getDescription());
        assertEquals("return book", matches.get(1).getDescription());
    }
}
