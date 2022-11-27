package ru.nsu.fit.oop.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.max;


/**
 * Class of the substring search.
 */
public class SubstringSearch {

    /**
     * Finds array of length of the longest native substring prefix
     *
     * @param str array of char we want to finds prefixes
     * @param len len of array of char we want to find
     * @return array of length of the longest native substring prefix
     */
    private static int[] prefixFunction(String str, int len){
        int[] values = new int[str.length()];
        for(int i = 0; i < len; ++i) values[i] = 0;
        for(int i = len; i < str.length(); ++i){
            int j = 0;
            while(i + j < str.length() && str.charAt(j) == str.charAt(i+j)){
                values[i+j] = max(values[i+j], j + 1);
                ++j;
            }
        }
        return values;
    }

    /**
     * Finds the indices of the occurrence of a substring {@param searchParam} in {@param file}.
     *
     * @param reader Reader where we want to search occurrence
     * @param str    String we want to find
     * @return array of indices of the occurrences. Count from 0
     * @throws IOException if some problems with a reading of the reader
     */
    public static List<Integer> zFunction(Reader reader, String str) throws IOException {

        if (reader == null) {
            throw new NullPointerException("File is null");
        }

        if (str == null) {
            throw new NullPointerException("Searched string is null");
        }

        List<Integer> indexes = new ArrayList<>();
        String text = "";
        try (BufferedReader br = new BufferedReader(reader)) {
            int c;
            while((c=br.read()) !=-1){
                text += (char)c;
            }
        }
        int[] prefixFunc = prefixFunction(str + '#' + text, str.length());

        for (int i = 0; i <= text.length(); ++i) {
            if (prefixFunc[i + str.length()] >= str.length())
                indexes.add(i - str.length());
        }

        return indexes;
    }

}


        /*
        int id = 0;
        List<Integer> indexes = new ArrayList<>();
        boolean eofFlag = false;

        try (BufferedReader br = new BufferedReader(reader)) {
            String buffArray = "";
            for (int i = 0; i < str.length(); ++i) {
                int c;
                if ((c = br.read()) == -1) {
                    eofFlag = true;
                    break;
                }
                buffArray += (char) c;
            }
            while (!eofFlag) {


                if (str.equals(buffArray)) {
                    indexes.add(id);
                }
                char[] tempChars = new char[str.length() - 1];
                buffArray.getChars(1, str.length(), tempChars, 0);
                buffArray = String.valueOf(tempChars);
                ++id;
                int c;
                if ((c = br.read()) == -1)
                    eofFlag = true;
                buffArray += (char) c;

            }
            return indexes;
        }
    }
} */
