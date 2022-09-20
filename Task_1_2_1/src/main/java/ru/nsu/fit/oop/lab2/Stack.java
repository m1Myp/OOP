package ru.nsu.fit.oop.lab2;

import java.lang.reflect.Array;
import java.util.EmptyStackException;

/**
 * Stack class.
 */
public class Stack < T > {

    private T[] arr;
    private int id;
    private final Class<T> stackClass;

    /**
     * Stack create.
     */
    @SuppressWarnings("unchecked")
    public Stack(int maxSize, Class<T> type)
    {
        stackClass = type;
        arr = (T[]) Array.newInstance(stackClass, maxSize);
        id = 0;
    }

    /**
     * size getter.
     *
     * @return Stack.AmountOfElems
     */
    public int getAmount() {

        return id;
    }

    @SuppressWarnings("unchecked")
    private void realloc() {
        T[] newArray = (T[]) Array.newInstance(stackClass, arr.length * 2);
        System.arraycopy(arr, 0, newArray, 0, id);
        arr = newArray;
    }

    /**
     * adding elem to the Stack and realloc array if needed.
     *
     * @param elem element for pushing
     */
    public void push(T elem) {
        if (id == arr.length) {
            realloc();
        }
        arr[id++] = elem;

    }

    /**
     * method for popping from stack.
     *
     * @return the last added elem from the stack
     * @throws EmptyStackException if you'll try to pop from empty stack.
     */
    public T pop() throws EmptyStackException {
        if (id == 0) {
            throw new EmptyStackException();
        }
        else {
            return arr[--id];
        }
    }

    /**
     * method for popping from stack.
     *
     * @return the n last element from stack like type stack
     * @throws EmptyStackException if you'll try to pop from empty stack..
     * ..or if count of pushing stack more than existing one
     */
    public Stack<T> popStack(int count) throws EmptyStackException {
        Stack<T> returnStack = new Stack<>(count, stackClass);
        if (id == 0 || id < count) {
            throw new EmptyStackException();
        }
        else {

            for (int i = 0; i < count; ++i) {
                returnStack.push(arr[id - 1]);
                arr[id - 1] = null;
                --id;
            }
        }
        return returnStack;
    }

    /**
     * adding elem from the one Stack to another Stack and realloc array if needed.
     *
     * @param pushingStack stack that we will push
     */
    public void pushStack(Stack<T> pushingStack) {
        int numOfElemsInPushingStack = pushingStack.id;
        for (int i = 0; i < numOfElemsInPushingStack; ++i) {
            if (id == arr.length) {
                realloc();
            }

            arr[id++] = pushingStack.arr[i];
        }
    }
}
