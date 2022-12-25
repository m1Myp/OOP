package ru.nsu.fit.oop.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tree tests class.
 */
public class TreeTest {
    @Test
    public void test0() {
        Tree<Integer> t = new Tree<>();
        Assertions.assertEquals(null, t.getParent());
        Assertions.assertThrows(NullPointerException.class, () -> t.value(null));
        Assertions.assertThrows(NullPointerException.class, () -> t.add(null));
        Assertions.assertThrows(NullPointerException.class, () -> t.add(null, 3));
        t.value(1);
        Tree<Integer> tt = t.add(2).add(3).add(4);
        Assertions.assertEquals(1, tt.getParent().getParent().getParent().getValue());
    }

    public Tree<Integer> treeA;

    /**
     * Initiating test tree for tests.
     */
    @BeforeEach
    public void init() {
        treeA = new Tree<>();
        treeA.value(1);
        Tree<Integer> treeB = treeA.add(3);
        treeB.add(4);
        Tree<Integer> treeC = treeA.add(6);
        treeC.add(9).add(10);
        Tree<Integer> treeD = treeA.add(7);
        treeA.add(treeD, 8);
        treeA.add(treeD, 12);
    }

    @Test
    public void testBreadthFirstSearch() {
        ArrayList<Integer> test = new ArrayList<>();
        Integer[] l = {1, 3, 6, 7, 4, 9, 8, 12, 10};
        Collections.addAll(test, l);
        Assertions.assertEquals(test, treeA.treeList());
        Tree<Integer> treeB = treeA.getChildren().get(1);
        Iterator<Integer> iterator = treeB.iterator();
        Integer next = iterator.next();
        Assertions.assertEquals(6, next);
        next = iterator.next();
        Assertions.assertEquals(9, next);
    }

    @Test
    public void testDepthFirstSearch() {
        ArrayList<Integer> test = new ArrayList<>();
        Integer[] l = {1, 3, 4, 6, 9, 10, 7, 8, 12};
        Collections.addAll(test, l);
        treeA.search = Tree.Search.DFS;
        Assertions.assertEquals(test, treeA.treeList());
    }

    @Test
    public void simple_test() {
        Tree<Integer> treeB = treeA.getChildren().get(1);
        treeA.remove(treeB.getValue());
        Assertions.assertEquals(2, treeA.getChildren().size());
        ArrayList<Integer> test = new ArrayList<>();
        Integer[] l = {1, 3, 7, 4, 8, 12};
        Collections.addAll(test, l);
        Assertions.assertEquals(test, treeA.treeList());
    }

    @Test
    public void testConcurrentModificationException() {
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            for (Object t : treeA) {
                treeA.getChildren().get(2).value(143);
            }
        });
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            for (Object t : treeA) {
                treeA.getChildren().get(1).remove(9);
            }
        });
    }

    @Test
    public void testSearchType() {
        treeA.search = null;
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Iterator<Integer> iterator = treeA.iterator();
        });
    }
}