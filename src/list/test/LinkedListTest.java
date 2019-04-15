package list.test;

import list.LinkedList;
import list.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    private List<String> list;

    @BeforeEach
    void setup() {
        list = new LinkedList<>();
    }

    @Test
    void addTest() {
        list.add("1");
        assertEquals(1, list.size());
        assertEquals("1", list.toString());

        list.add("2");
        list.add("3");
        list.add("4");

        assertEquals(4, list.size());
        assertEquals("1, 2, 3, 4", list.toString());
    }

    @Test
    void removeTest() {
        list.add("1");
        assertEquals("1", list.remove());
        assertEquals("", list.toString());
        assertEquals(0, list.size());

        list.add("1");
        list.add("2");
        list.add("3");

        assertEquals("1", list.remove());
        assertEquals(2, list.size());
        assertEquals("2, 3", list.toString());

        assertEquals("2", list.remove());
        assertEquals(1, list.size());
        assertEquals("3", list.toString());

        assertEquals("3", list.remove());
        assertEquals(0, list.size());
        assertEquals("", list.toString());
    }

    @Test
    void removeErrorTest() {
        Exception ex = assertThrows(IllegalStateException.class, () -> list.remove());
        assertEquals("The LinkedList is empty", ex.getMessage());
    }

    @Test
    void insertTest() {
        list.insert("1", 0);
        assertEquals(1, list.size());
        assertEquals("1", list.toString());

        list.insert("0", 0);
        assertEquals(2, list.size());
        assertEquals("0, 1", list.toString());

        list.insert("0.5", 1);
        assertEquals(3, list.size());
        assertEquals("0, 0.5, 1", list.toString());

        list.insert("2", 3);
        assertEquals(4, list.size());
        assertEquals("0, 0.5, 1, 2", list.toString());

        list.insert("0.75", 2);
        assertEquals(5, list.size());
        assertEquals("0, 0.5, 0.75, 1, 2", list.toString());
    }

    @Test
    void insertErrorTest() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> list.insert("1", 1));
        assertEquals("Position is greater than length of list", ex.getMessage());
    }

    @Test
    void removeAtTest() {
        list.add("0");
        assertEquals("0", list.removeAt(0));
        assertEquals(0, list.size());

        list.add("0");
        list.add("1");
        list.add("2");
        assertEquals("0", list.removeAt(0));
        assertEquals(2, list.size());
        assertEquals("1, 2", list.toString());

        assertEquals("2", list.removeAt(1));
        assertEquals(1, list.size());
        assertEquals("1", list.toString());

        list.add("2");
        list.add("3");

        assertEquals("2", list.removeAt(1));
        assertEquals(2, list.size());
        assertEquals("1, 3", list.toString());
    }

    @Test
    void removeAtErrorTest() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> list.removeAt(1));
        assertEquals("Position is greater than length of list", ex.getMessage());
    }

    @Test
    void getTest() {
        list.add("0");
        assertEquals("0", list.get(0));

        list.add("1");
        assertEquals("0", list.get(0));
        assertEquals("1", list.get(1));

        list.add("2");
        assertEquals("0", list.get(0));
        assertEquals("1", list.get(1));
        assertEquals("2", list.get(2));
    }

    @Test
    void getErrorTest() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> list.get(1));
        assertEquals("Position is greater than length of list or list is empty", ex.getMessage());
    }

    @Test
    void findTest() {
        assertEquals(-1, list.find("1"));

        list.add("1");
        assertEquals(0, list.find("1"));

        list.add("2");
        list.add("3");
        assertEquals(0, list.find("1"));
        assertEquals(1, list.find("2"));
        assertEquals(2, list.find("3"));
    }

    @Test
    void sizeTest() {
        assertEquals(0, list.size());
        list.add("1");
        assertEquals(1, list.size());
        list.remove();
        assertEquals(0, list.size());
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(3, list.size());
        list.remove();
        list.remove();
        assertEquals(1, list.size());
    }
}
