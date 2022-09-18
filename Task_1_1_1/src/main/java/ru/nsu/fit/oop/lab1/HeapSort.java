package ru.nsu.fit.oop.lab1;


import java.util.Arrays;

public class HeapSort {
    /**
     * Swap two elements
     *
     * @param arr - array where elements swaps
     * @param x,y - id of this elements
     */
    private static void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    /**
     * Compares current id's element with his two children
     * if parent greater then children swap them. Repeats this action before end of heap
     *
     * @param arr heap
     * @param N   len of heap
     * @param id  index of element we should sift down
     */
    private static void siftDown(int[] arr, int id, int N) {
        int current = id;
        int left = id * 2 + 1;
        int right = id * 2 + 2;
        if (left < N && arr[current] > arr[left])
            current = left;
        if (right < N && arr[current] > arr[right])
            current = right;
        if (current != id) {
            swap(arr, current, id);
            siftDown(arr, current, N);
        }
    }

    /**
     * Find root of the tree (heap) preserving the heap invariant
     * Swap with elem in last pos
     *
     * @param arr heap
     * @param len len of heap
     * @return the largest element of the heap (root of the tree)
     */
    private static void takeRoot(int[] arr, int len) {
        int last = len - 1;
        swap(arr, 0, last);
        siftDown(arr, 0, len - 1);
    }

    /**
     * Heap sort of array
     *
     * @param arr array to sort
     */
    public static void heapSort(int[] arr) {
        int len = arr.length;
        for (int i = len-1; i >= 0; i--)
            siftDown(arr, i, len);

        while(len > 0) {
            takeRoot(arr, len);
            --len;
        }
        for(int j = 0; j < arr.length/2; ++j){
            swap(arr, j, (arr.length-1)-j);
        }
    }
}
