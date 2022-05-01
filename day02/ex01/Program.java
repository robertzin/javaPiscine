import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Program {

    public static ArrayList<String> dictionaryA;
    public static ArrayList<String> dictionaryB;
    public static TreeSet<String> toWrite;

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: \"java Program.java [fileA] [fileB]\"");
            System.exit(1);
        }
        makeDictionary(args);
        String str = String.format("%.2f", cosineSimilarity());
        System.out.println("Similarity = " + str);
    }

    public static void makeDictionary(String[] args) {
        dictionaryA = new ArrayList<>();
        dictionaryB = new ArrayList<>();

        String outputFilename = "/Users/robert_zin/Documents/42Cursus/_Piscine_Java/day02/ex01/dictionary.txt";
        try (BufferedReader fileA = new BufferedReader(new FileReader(args[0]));
             BufferedReader fileB = new BufferedReader(new FileReader(args[1]));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename, false))) {

            while (fileA.ready()) {
                String[] tmp = fileA.readLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                dictionaryA.addAll(Arrays.asList(tmp));
            }
            while (fileB.ready()) {
                String[] tmp = fileB.readLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                dictionaryB.addAll(Arrays.asList(tmp));
            }


            toWrite = new TreeSet<>(dictionaryA);
            toWrite.addAll(dictionaryB);

            for (String str: toWrite) {
                writer.write(str);
                writer.write(' ');
            }
            writer.flush();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    public static double cosineSimilarity() {

        String[] dictionary = toWrite.toArray(new String[toWrite.size()]);

        int[] freqA = countFreq(dictionary, dictionaryA);
        int[] freqB = countFreq(dictionary, dictionaryB);

        double numerator = numeratorFunc(freqA, freqB);
        double denumerator = denumeratorFunc(freqA, freqB);

        if (numerator == 0 || denumerator == 0)
            return 0;
        return numerator / denumerator;
    }

    public static int[] countFreq(String[] dictionary, ArrayList<String> someDictionary) {
        int[] freq = new int[toWrite.size()];

        for (int i = 0; i < toWrite.size(); i++) {
            for (String str: someDictionary) {
                if (dictionary[i].equals(str))
                    freq[i]++;
            }
        }
        return freq;
}

    public static int numeratorFunc(int[] arrA, int[] arrB) {
        int ret = 0;
        int len = arrA.length;

        for (int i = 0; i < len; i++) {
            ret += arrA[i] * arrB[i];
        }
        return ret;
    }

    public static double denumeratorFunc(int[] arrA, int[] arrB) {
        double denA = 0;
        double denB = 0;

        for (int i = 0; i < arrA.length; i++) {
            denA += arrA[i] * arrA[i];
        }
        for (int i = 0; i < arrB.length; i++) {
            denB += arrB[i] * arrB[i];
        }
        return Math.sqrt(denA) * Math.sqrt(denB);
    }
}
