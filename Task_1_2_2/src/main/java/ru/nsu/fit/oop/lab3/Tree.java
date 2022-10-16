package ru.nsu.fit.oop.lab3;

import java.util.*;

/**
 * Tree class.
 */
public class Tree<T> implements Collection<T> {
    private int size = 0;
    private T root;
    private Map<T, T[]> treeMap;
    private Tree<T>[] subtree;
    private int modificationCount;
    public String searchType;

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
        return new TreeIterator(searchType);
    }

    /**
     * Add new member into tree.
     *
     * @param member new tree member
     * @return true if this collections changed as a result of the call.
     * @throws NullPointerException if you try add NULL object
     */
    @SuppressWarnings("unchecked")
    public boolean add(T member) {
        if (member == null) {
            throw new NullPointerException();
        }

        T[] treeArr = treeMap.get(root);
        T[] newTreeArr = (T[]) new Object[treeArr.length + 1];
        System.arraycopy(treeArr, 0, newTreeArr, 0, treeArr.length);
        newTreeArr[treeArr.length] = member;

        treeMap.put(root, newTreeArr);

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

        T node = findNode(value);
        if (node == null) {
            return false;
        }

        if(treeMap.get(value) == null){
            T parentNode = findParent(value);
            shiftSubtree(node, parentNode);
        }

        treeMap.remove(member);

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
        treeMap.clear();
        modificationCount++;
    }

    /**
     * Finding of node with 'value' element on it
     *
     * @param value member we want find a node
     * @return node
     */
    private T findParent(T value) {
        T current = root;
        boolean flag = false;
        while(treeMap.get(current) != null) {
            T[] subtrees = treeMap.get(current);
            for (int i = 0; i < subtrees.length; ++i){
                if(subtrees[i] == value) {
                    flag = true;
                    break;
                }
                else {
                    current = findNode(subtrees[i]);
                }
            }
            if(flag) {
                break;
            }
        }
        return current;
    }

    /**
     * Finding of node with 'value' element on it
     *
     * @param value member we want find a node
     * @return node
     */
    private T findNode(T value) {
        T current = root;
        boolean flag = false;
        while(treeMap.get(current) != null) {
            T[] subtrees = treeMap.get(current);
            for (int i = 0; i < subtrees.length; ++i){
                if(subtrees[i] == value) {
                    current = subtrees[i];
                    flag = true;
                    break;
                }
                else {
                    current = findNode(subtrees[i]);
                }
            }
            if(flag) {
                break;
            }
        }
        return current;
    }

    /**
     * Shift subtree to new position
     *
     * @param first node we need to remove
     * @param second node we need shift to new position
     *
     * @throws ClassCastException if object[] cant cast to T[]
     */
    private void shiftSubtree(T first, T second) {
        T[] subtrees = treeMap.get(second);
        for(int i = 0; i < subtrees.length; ++i){
            if (subtrees[i] == first){
                subtrees[i] = treeMap.get(first)[0];
            }
        }
        T[] newSubtree = (T[]) new Object[subtrees.length + treeMap.get(first).length + 1];
        System.arraycopy(subtrees, 0, newSubtree, 0, subtrees.length);
        for(int i = 1; i < treeMap.get(first).length; ++i){
            newSubtree[subtrees.length + i - 1] = treeMap.get(first)[i];
        }
        treeMap.put(second, newSubtree);
    }

    /**
     * Not supported.
     *
     * @throws UnsupportedOperationException optional thing in our task
     */
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported.
     *
     * @throws UnsupportedOperationException optional thing in our task
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported.
     *
     * @throws UnsupportedOperationException optional thing
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }


    /**
     * Tree iterator class
     */
    class TreeIterator implements Iterator<T> {
        private final int expectedModificationCount = modificationCount;
        private T next;

        /**
         * Tree iterator.
         */
        TreeIterator(String flagOfSearch) {
            next = root;
            if (next == null) {
                return;
            }

        }

        /**
         * @throws ConcurrentModificationException if we try to change collection
         *                                         while iterate it.
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
         * false otherwise.
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
            return next;
        }
    }
}
