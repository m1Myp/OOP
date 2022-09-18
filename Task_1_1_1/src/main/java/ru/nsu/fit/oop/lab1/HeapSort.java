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
    public static void siftDown(int[] array, int i, int len) {
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        if (l >= len && r >= len) return;
        if (l < len && r < len) {
            int m;
            if (array[l] < array[r]) {
                m = l;
            } else m = r;
            if (array[m] < array[i]) {
                swap(array, i, m);
                siftDown(array, m, len);
            }
        } else if (l < len && array[l] < array[i]) {
            swap(array, i, l);
            siftDown(array, l, len);
        }
    }

    public static void siftUp(int v, int[] array) {
        int f = (v - 1) / 2;
        if (v == 0) return;
        if (array[v] < array[f]) {
            swap(array, v, f);
            siftUp(f, array);
        }
    }

/*
        int i = 0;
        int j = 0;

        while(i < text.length()){
            if(searchParam.charAt(j) == text.charAt(i)){
                ++j;
                ++i;
            }
            if(j == searchParam.length()){
                indexes.add(i-j);
                j = prefixFunc[j-1 + searchParam.length()];
            }
            else if(i < text.length() && searchParam.charAt(j) != text.charAt(i)){
                if(j != 0)
                    j = prefixFunc[j-1 + searchParam.length()];
                else
                    ++i;
            }
        }
        */
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
        int[] sortedArr = arr.clone();
        for (int i = len-1; i >= 0; i--) {
            siftDown(arr, i, len);
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("down");
        for (int i = 0; i < len; i++) {
            siftUp(i, sortedArr);
            System.out.println(Arrays.toString(sortedArr));
        }
        System.out.println("up");
        while(len > 0) {
            takeRoot(arr, len);
            --len;
        }
        for(int j = 0; j < arr.length/2; ++j){
            swap(arr, j, (arr.length-1)-j);
        }
    }
}
