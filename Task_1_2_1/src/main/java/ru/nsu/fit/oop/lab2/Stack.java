package ru.nsu.fit.oop.lab2;

import java.util.EmptyStackException;

/**
 * Stack class.
 */
public class Stack<T> {

    private T[] arr;
    private int index;

    /**
     * Stack create.
     */
    @SuppressWarnings("unchecked")
    public Stack(int maxSize) {
        arr = (T[]) new Object[maxSize];
        index = 0;
    }

    /**
     * size getter.
     *
     * @return Stack.AmountOfElems
     */
    public int getAmount() {
        return index;
    }

    @SuppressWarnings("unchecked")
    private void realloc() {
        T[] newArray = (T[]) new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArray, 0, index);
        arr = newArray;
    }

    /**
     * adding elem to the Stack and realloc array if needed.
     *
     * @param elem element for pushing
     */
    public void push(T elem) {
        if (elem != null) {
            if (index == arr.length) {
                realloc();
            }
            arr[index++] = elem;
        }
    }

    /**
     * method for popping from stack.
     *
     * @return the last added elem from the stack
     * @throws EmptyStackException if you'll try to pop from empty stack.
     */
    public T pop() throws EmptyStackException {
        if (index == 0) {
            throw new EmptyStackException();
        } else {
            T temp = arr[--index];
            T[] newSmallArray = (T[]) new Object[index];
            System.arraycopy(arr, 0, newSmallArray, 0, index);
            arr = newSmallArray;
            return temp;
        }
    }

    /**
     * method for popping from stack.
     *
     * @return the n last element from stack like type stack
     * @throws EmptyStackException if you'll try to pop from empty stack..
     *                             ..or if count of pushing stack more than existing one
     */
    public Stack<T> popStack(int count) throws EmptyStackException {
        if (index == 0 || index < count) {
            throw new EmptyStackException();
        } else {
            Stack<T> returnStack = new Stack<>(count);
            for (int i = 0; i < count; ++i) {
                returnStack.push(arr[index - 1]);
                arr[index - 1] = null;
                --index;
            }
            return returnStack;
        }
    }

    /**
     * adding elem from the one Stack to another Stack and realloc array if needed.
     *
     * @param pushingStack stack that we will push
     */
    public void pushStack(Stack<T> pushingStack) {
        int numOfElemsInPushingStack = pushingStack.index;
        for (int i = 0; i < numOfElemsInPushingStack; ++i) {
            if (index == arr.length) {
                realloc();
            }

            arr[index++] = pushingStack.arr[i];
        }
    }
}
