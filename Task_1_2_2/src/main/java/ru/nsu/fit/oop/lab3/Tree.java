package ru.nsu.fit.oop.lab3;

import java.util.*;

/**
 * Tree class.
 */
public class Tree<T extends Comparable<T>> implements Collection<T> {
    private int size = 0;
    private Node root;
    private int modificationCount;

    /**
     * @return tree size.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if tree is empty, otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Checking object o is member of tree
     *
     * @param object is object we need to check
     *
     * @return true or false
     * @throws NullPointerException if you try check NULL object
     * @throws ClassCastException if object cant cast to T
     */
    @Override
    public boolean contains(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }

        T member = (T) object;

        return (findNode(member) != null);
    }

    /**
     * @return new TreeIterator
     */
    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    /**
     * Add new member into tree.
     *
     * @param member new tree member
     * @return true if this collections changed as a result of the call.
     * @throws NullPointerException if you try add NULL object
     */
    @Override
    public boolean add(T member) {
        if (member == null) {
            throw new NullPointerException();
        }
        Node parent = null;
        Node current = root;
        while (current != null) {
            parent = current;
            if (member.compareTo(current.member) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        Node newNode = new Node(member);
        if (parent == null) {
            root = newNode;
        } else if (member.compareTo(parent.member) < 0) {
            newNode.parent = parent;
            parent.left = newNode;
        } else {
            newNode.parent = parent;
            parent.right = newNode;
        }

        ++size;
        ++modificationCount;
        return true;
    }

    /**
     * Remove new member into tree.
     *
     * @param member new tree member
     * @return true if an element was removed as a result of this call
     *         false otherwise
     * @throws NullPointerException if you try remove NULL object
     * @throws ClassCastException if object cant cast to T
     */
    @Override
    public boolean remove(Object member) {
        if (member == null) {
            throw new NullPointerException();
        }

        T value = (T) member;

        Node node = findNode(value);
        if (node == null) {
            return false;
        }

        if (node.left == null) {
            shiftSubtree(node, node.right);
        } else if (node.right == null) {
            shiftSubtree(node, node.left);
        } else {
            Node nodeChild = child(node);
            if (nodeChild.parent != node) {
                shiftSubtree(nodeChild, nodeChild.right);
                nodeChild.right = node.right;
                nodeChild.right.parent = nodeChild;
            }
            shiftSubtree(node, nodeChild);
            nodeChild.left = node.left;
            nodeChild.left.parent = nodeChild;
        }
        --size;
        ++modificationCount;
        return true;
    }

    /**
     * Checking of contains collection into our collection
     *
     * @param collection collection we want check in ours
     * @return true if contains
     *         false otherwise
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (var elements : collection) {
            if(!contains(elements)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add collection into our collection
     *
     * @param collection collection we want to add
     * @return true if we add successfully
     *         false otherwise
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean answer = false;
        for (var elements : collection) {
            if(add(elements)) {
                answer = true;
            }
        }
        return answer;
    }

    /**
     * Remove collection into our collection
     *
     * @param collection collection we want to remove
     * @return true if we remove successfully
     *         false otherwise
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean answer = false;
        for (var elements : collection) {
            if(remove(elements)) {
                answer = true;
            }
        }
        return answer;
    }

    /**
     * Tree clearing.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
        modificationCount++;
    }

    /**
     * Finding of node with 'value' element on it
     *
     * @param value member we want find a node
     * @return node
     */
    private Node findNode(T value) {
        Node current = root;
        while (current != null && !value.equals(current.member)) {
            if (value.compareTo(current.member) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    /**
     * Shift subtree to new position
     *
     * @param first node we need to remove
     * @param second node we need shift to new position
     */
    private void shiftSubtree(Node first, Node second) {
        if (first.parent == null) {
            root = second;
        } else if (first == first.parent.left) {
            first.parent.left = second;
        } else {
            first.parent.right = second;
        }
        if (second != null) {
            second.parent = first.parent;
        }
    }

    /**
     * Find the most left child of the right child current node
     *
     * @param node node we need find their most left child
     *             of the current right child
     * @return new node
     */
    private Node child(Node node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Idk how realize this now.
     * Need I?
     *
     * @throws UnsupportedOperationException optional thing in our task i guess
     */
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    /**
     * Idk how realize this now.
     * Need I?
     *
     * @throws UnsupportedOperationException optional thing in our task i guess
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws UnsupportedOperationException optional thing
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    /**
     * Node class.
     */
    class Node {
        public Node left;
        T member;
        Node right;
        Node parent;

        Node(T value) {
            this.member = value;
        }
    }

    /**
     * Tree iterator class
     */
    class TreeIterator implements Iterator<T> {
        private final int expectedModificationCount = modificationCount;
        private Node next;

        /**
         * Tree iterator (left side).
         */
        TreeIterator() {
            next = root;
            if (next == null) {
                return;
            }
            while (next.left != null) {
                next = next.left;
            }
        }

        /**
         * @throws ConcurrentModificationException if we try to change collection
         * while iterate it.
         */
        private void checkModification() {
            if (modificationCount != expectedModificationCount) {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * Check that we did not change collection
         *
         * @return true if iterator find next member in tree.
         *         false otherwise.
         */
        @Override
        public boolean hasNext() {
            checkModification();
            return (next != null);
        }

        /**
         * Check that we did not change collection and find next element.
         *
         * @return next element.
         * @throws NoSuchElementException if we try use next for no child member
         */
        @Override
        public T next() {
            checkModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node answer = next;

            if (next.right != null) {
                next = next.right;
                while (next.left != null) {
                    next = next.left;
                }
                return answer.member;
            }

            while (true) {
                if (next.parent == null) {
                    next = null;
                    return answer.member;
                }
                if (next.parent.left == next) {
                    next = next.parent;
                    return answer.member;
                }
                next = next.parent;
            }
        }
    }
}