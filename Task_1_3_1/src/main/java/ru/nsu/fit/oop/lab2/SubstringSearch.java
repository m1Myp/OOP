package ru.nsu.fit.oop.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Class of the substring search.
 */
public class SubstringSearch {

    /**
     * Finds the indices of the occurrence of a substring {@param searchParam} in {@param file}.
     *
     * @param file Reader where we want to search occurrence
     * @param str  String we want to find
     * @return array of indices of the occurrences. Count from 0
     * @throws IOException if some problems with a reading of the file
     */
    public static ArrayList<Integer>
    finiteStateMashine(Reader file, String str) throws IOException {
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        if (str == null) {
            throw new NullPointerException("Searched string is null");
        }

        int id = 0;
        ArrayList<Integer> indexes = new ArrayList<>();
        boolean eofFlag = false;
        try (BufferedReader br = new BufferedReader(file)) {
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
}
