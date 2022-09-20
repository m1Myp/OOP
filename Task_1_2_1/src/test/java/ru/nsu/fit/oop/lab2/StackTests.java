package ru.nsu.fit.oop.lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;

/**
 * Stack testing class.
 */
public class StackTests {

    @Test
    public void testTypeInt() {
        Stack<Integer> s = new Stack<>(5, Integer.class);
        for (int i = 0; i < 5; i++) {
            s.push(i);
        }

        for (int i = 4; i >= 0; i--) {
            assertEquals(i, s.pop());
            assertEquals(i, s.getAmount());
        }
    }

    @Test
    public void testTypeString() {
        Stack<String> s = new Stack<>(3, String.class);
        s.push("I");
        s.push("Love");
        s.push("Programming");
        assertEquals("Programming", s.pop());
        assertEquals("Love", s.pop());
        assertEquals("I", s.pop());
        assertEquals(0, s.getAmount());
    }

    @Test
    public void testPopStack() {
        Stack<String> s = new Stack<>(3, String.class);
        s.push("I");
        s.push("Love");
        s.push("Programming");
        Stack<String> temp = s.popStack(2);
        assertEquals(1, s.getAmount());
        assertEquals("I", s.pop());
    }

    @Test
    public void testPushStack() {
        Stack<Integer> temp = new Stack<>(2, Integer.class);
        temp.push(0);
        temp.push(1);
        temp.push(2);
        Stack<Integer> s = new Stack<>(3, Integer.class);
        s.push(3);
        s.pushStack(temp);
        for (int i = 2; i >= 0; --i) {
            assertEquals(i, s.pop());
            assertEquals(i + 1, s.getAmount());
        }
        assertEquals(3, s.pop());
    }

    @Test
    public void testOfRealloc() {
        Stack<Integer> s = new Stack<>(1, Integer.class);
        for (int i = 0; i < 100; i++) {
            s.push(i);
        }
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, (int) s.pop());
        }
    }

    @Test
    public void testPopException() {
        assertThrows(EmptyStackException.class,
                () -> {
                    Stack<Integer> s = new Stack<>(3, Integer.class);
                    s.pop();
                });
    }

    @Test
    public void testPopStackException() {
        assertThrows(EmptyStackException.class,
                () -> {
                    Stack<String> s = new Stack<>(3, String.class);
                    s.push("I");
                    s.push("Love");
                    s.push("Programming");
                    s.popStack(55);
                });
    }
}
