package stack.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stack.ListStack;
import stack.Stack;

import static org.junit.jupiter.api.Assertions.*;

class ListStackTest {

    private Stack<Integer> stack;

    @BeforeEach
    void setup() {
        stack = new ListStack<>();
    }

    @Test
    void pushTest() {
        for (int i = 0; i < 1001; i++) {
            stack.push(i);
        }
        assertEquals(1001, stack.size());
    }

    @Test
    void containsTest() {
        stack.push(5);
        assertTrue(stack.contains(5));
        assertFalse(stack.contains(6));
    }

    @Test
    void popTest() {
        stack.push(5);
        assertEquals(5, stack.pop());
    }

    @Test
    void popTestError() {
        Exception ex = assertThrows(IllegalStateException.class, () -> stack.pop());
        assertEquals("No more items on the stack", ex.getMessage());
    }

    @Test
    void sizeTest() {
        assertEquals(0, stack.size());

        stack.push(5);
        assertEquals(1, stack.size());

        stack.push(6);
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    void accessTest() {
        stack.push(5);
        assertEquals(5, stack.access(5));
        assertEquals(0, stack.size()); // assert access is destructive

        stack.push(5);
        stack.push(6);
        assertEquals(6, stack.access(6));
        assertEquals(1, stack.size());

        stack.push(6);
        assertEquals(5, stack.access(5));
        assertEquals(0, stack.size());
    }

    @Test
    void accessTestError() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> stack.access(5));
        assertEquals("Could not find item on the stack: 5", ex.getMessage());
    }
}
