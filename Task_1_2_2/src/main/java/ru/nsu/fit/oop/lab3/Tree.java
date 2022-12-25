package ru.nsu.fit.oop.lab3;

import java.util.*;
/**
 * Tree class
 *
 * @param <T> type of Tree elements
 */
public class Tree<T> implements Iterable<T> {
    private T value;
    private Tree<T> parent;
    private final ArrayList<Tree<T>> children;
    public Search search;
    private int checkCount;

    /**
     * Constructor for new Tree element. Search type is set to BFS by default.
     */
    public Tree() {
        value = null;
        parent = null;
        children = new ArrayList<>();
        search = Search.BFS;
    }

    /**
     * Traversal types for iterating over elements.
     */
    enum Search {
        BFS,
        DFS
    }

    /**
     * Returns iterator over tree elements with specified traversal type.
     *
     * @throws IllegalStateException if traversal type is not specified
     */

    @Override
    public Iterator<T> iterator() throws IllegalStateException {
        if (search == Search.BFS) {
            return new BreadthFirstSearch(this);
        } else if (search == Search.DFS) {
            return new DepthFirstSearch(this);
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Updates tree checkCount up to the root. Helps to track tree modification.
     */
    private void update() {
        Tree<T> element = this;
        while (element.parent != null) {
            element.checkCount++;
            element = element.parent;
        }
        element.checkCount++;
    }

    /**
     * Sets value for a tree element.
     *
     * @param newValue value to be set
     * @throws NullPointerException if the value is not specified
     */
    public void value(T newValue) throws NullPointerException {
        if (newValue == null) {
            throw new NullPointerException();
        }
        update();
        this.value = newValue;
    }

    /**
     * Returns the value of the element.
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns the descendants of the tree element.
     */
    public ArrayList<Tree<T>> getChildren() {
        return children;
    }

    /**
     * Returns parent of the tree element.
     */
    public Tree<T> getParent() {
        return parent;
    }

    /**
     * Adds an element to a tree's descendants.
     *
     * @return new Tree element with the specified value
     * @throws NullPointerException if the value is not specified
     */
    public Tree<T> add(T newValue) throws NullPointerException {
        if (newValue == null) {
            throw new NullPointerException("Cannot add \"null\" element to a tree.");
        }
        Tree<T> element = new Tree<>();
        element.value = newValue;
        element.parent = this;
        element.search = this.search;
        update();
        children.add(element);
        return element;
    }

    /**
     * Adds an element to the specified subtree's descendants.
     *
     * @throws NullPointerException if tree element is not specified
     */
    public Tree<T> add(Tree<T> tree, T newValue) throws NullPointerException {
        if (tree == null) {
            throw new NullPointerException("Tree node must be specified.");
        }
        return tree.add(newValue);
    }

    /**
     * Removes a subtree with given value at the root if such a subtree exists in origin tree.
     *
     * @param delValue the value of element to be deleted
     * @return {@code true} if the element was found and deleted
     */
    public boolean remove(T delValue) {
        for (int i = 0; i < this.children.size(); i++) {
            Tree<T> t = this.children.get(i);
            if (t.value == delValue) {
                update();
                t.removeChildren();
                this.children.remove(t);
                return true;
            }
            t.remove(delValue);
        }
        return false;
    }

    private void removeChildren() {
        for (int i = this.children.size() - 1; i >= 0; i--) {
            if (!this.children.get(i).children.isEmpty()) {
                this.children.get(i).removeChildren();
            }
            this.children.remove(i);
        }
    }

    /**
     * Returns a list of ordered tree elements' values.
     */
    public ArrayList<T> treeList() {
        T curr;
        ArrayList<T> list = new ArrayList<>();
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            curr = iterator.next();
            list.add(curr);
        }
        return list;
    }

    /**
     * Breadth First Search iterator for Tree elements.
     */
    public class BreadthFirstSearch implements Iterator<T> {
        private final Tree<T> root;
        private final Queue<Tree<T>> queue;
        private final int check;

        /**
         * Initiates a queue with a root of a tree.
         */
        public BreadthFirstSearch(Tree<T> root) {
            queue = new LinkedList<>();
            this.root = root;
            check = root.checkCount;
            queue.add(this.root);
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return (!queue.isEmpty());
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() throws NoSuchElementException, ConcurrentModificationException {
            if (check < root.checkCount) {
                throw new ConcurrentModificationException();
            }
            if (queue.isEmpty()) {
                throw new NoSuchElementException();
            }
            Tree<T> next = queue.poll();
            queue.addAll(next.children);
            return next.getValue();
        }
    }

    /**
     * Depth First Search iterator for Tree elements.
     */
    public class DepthFirstSearch implements Iterator<T> {
        private final Tree<T> root;
        private final Stack<Tree<T>> stack;
        private final int check;

        /**
         * Initiates a stack with a root of a tree.
         */
        public DepthFirstSearch(Tree<T> root) {
            stack = new Stack<>();
            this.root = root;
            check = this.root.checkCount;
            stack.push(this.root);
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return (!stack.isEmpty());
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() throws NoSuchElementException, ConcurrentModificationException {
            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }
            if (check < root.checkCount) {
                throw new ConcurrentModificationException();
            }
            Tree<T> next = stack.pop();
            int i = next.children.size() - 1;
            for (; i >= 0; i--) {
                stack.push(next.children.get(i));
            }
            return next.getValue();
        }
    }
}