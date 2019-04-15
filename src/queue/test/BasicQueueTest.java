package queue.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue.BasicQueue;
import queue.Queue;

import static org.junit.jupiter.api.Assertions.*;

class BasicQueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    void setup() {
        queue = new BasicQueue<>();
    }


    @Test
    void enqueueTest() {
        for (int i = 0; i < 100; i++) {
            queue.enQueue(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(i, queue.deQueue());
        }
        for (int i = 0; i < 100; i++) {
            queue.enQueue(i);
            assertEquals(i, queue.deQueue());
        }
    }

    @Test
    void dequeueTest() {
        queue.enQueue(5);
        assertEquals(5, queue.deQueue());

        queue.enQueue(5);
        queue.enQueue(6);
        queue.enQueue(7);

        assertEquals(5, queue.deQueue());
        assertEquals(6, queue.deQueue());

        queue.enQueue(8);

        assertEquals(7, queue.deQueue());
        assertEquals(8, queue.deQueue());
    }

    @Test
    void dequeueError() {
        Exception ex = assertThrows(IllegalStateException.class, () -> queue.deQueue());
        assertEquals("No items in queue", ex.getMessage());
    }

    @Test
    void sizeTest() {
        assertEquals(0, queue.size());
        queue.enQueue(1);
        assertEquals(1, queue.size());
        queue.deQueue();
        assertEquals(0, queue.size());

        for (int i = 0; i < 100; i++) {
            queue.enQueue(i);
            assertEquals(i + 1, queue.size());
        }
    }

    @Test
    void containsTest() {
        assertFalse(queue.contains(1));
        queue.enQueue(1);
        assertTrue(queue.contains(1));
        queue.enQueue(2);
        queue.enQueue(3);
        assertTrue(queue.contains(2));
        assertTrue(queue.contains(3));
        assertFalse(queue.contains(4));
    }

    @Test
    void accessItemTest() {
        Queue<String> stringQueue = new BasicQueue<>();
        assertNull(stringQueue.access("5"));

        stringQueue.enQueue("5");
        assertEquals("5", stringQueue.access("5"));
        assertEquals(0, stringQueue.size());

        stringQueue.enQueue("5");
        stringQueue.enQueue("6");
        stringQueue.enQueue("7");

        assertEquals("5", stringQueue.access("5"));
        assertEquals(2, stringQueue.size());
        assertEquals("7", stringQueue.access("7"));
        assertEquals(0, stringQueue.size());

        stringQueue.enQueue("5");
        stringQueue.enQueue("6");
        stringQueue.enQueue("7");

        assertNull(stringQueue.access("8"));
        assertEquals(0, stringQueue.size());
    }

    @Test
    void accessPositionTest() {
        Queue<String> stringQueue = new BasicQueue<>();
        assertNull(stringQueue.access(5));

        stringQueue.enQueue("5");
        assertEquals("5", stringQueue.access(0));
        assertEquals(0, stringQueue.size());

        stringQueue.enQueue("5");
        stringQueue.enQueue("6");
        stringQueue.enQueue("7");

        assertEquals("5", stringQueue.access(0));
        assertEquals(2, stringQueue.size());
        assertEquals("7", stringQueue.access(1));
        assertEquals(0, stringQueue.size());

        stringQueue.enQueue("5");
        stringQueue.enQueue("6");
        stringQueue.enQueue("7");

        assertNull(stringQueue.access(5));
        assertEquals(3, stringQueue.size());
    }
}
