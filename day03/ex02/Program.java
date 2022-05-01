import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Program {

    private static int  arraySize;
    private static int threadsCount;
    private static int step;
    private static int pos = 0;
    private static ArrayList<int[]> arrayList;
    private static ArrayList<Thread> threads;


    public static void checkArgs(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: \"java Pogram --arraySize=[num] --threadsCount=[num]\"");
            System.exit(1);
        }
        else if (!args[0].contains("--arraySize=") || !args[1].contains("--threadsCount=")) {
            System.err.println("Usage: \"java Pogram --arraySize=[num] --threadsCount=[num]\"");
            System.exit(1);
        }

        arraySize = Integer.parseInt(args[0].substring(12));
        threadsCount = Integer.parseInt(args[1].substring(15));

        if (arraySize <= 0 || threadsCount <= 0) {
            System.err.println("Error! All [num] should be greater than 0");
            System.exit(1);
        }
        else if (arraySize > 2_000_000) {
            System.err.println("Error! Array size should be less than 2_000_000");
            System.exit(1);
        }
        else if (threadsCount > arraySize) {
            System.err.println("Error! Threads count should be less or equal arraySize");
            System.exit(1);
        }
    }

    public static void divideArray() {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = (int) Math.floor(Math.random() * (arraySize - 1 + 1) + 1);
        }

        arrayList = new ArrayList<>();
        step = arraySize / threadsCount + 1;
        int i = 0;

        for (i = 0; i < arraySize - step; i += step) {
            arrayList.add(Arrays.copyOfRange(array, i, i + step));
        }
        arrayList.add(Arrays.copyOfRange(array, i, arraySize));
    }

    public static void standardCountValues(ArrayList<int[]> arrays) {
        long res = 0;

        for (int[] arr: arrays) {
            for (int num : arr) {
                res += num;
            }
        }
        System.out.println("Sum: " + res);
    }


//    public static long countValues(int[] array) {
//        long ret = 0;
//
//        for (int j : array) {
//            ret += j;
//        }
//
//        int param;
//        param = Math.min((pos + step - 1), (arraySize - 1));
//        System.out.print(Thread.currentThread().getName() + ": ");
//        System.out.print("from " + pos + " to " + param + " sum is " + ret);
//        System.out.println();
//        pos += step;
//        return ret;
//    }

    public static void main(String[] args) {
        checkArgs(args);
        divideArray();
        standardCountValues(arrayList);
        threads = new ArrayList<>();

        long done = 0;
        for (int[] arr : arrayList) {
            int param;
            param = Math.min((pos + step - 1), (arraySize - 1));
            threads.add(new CustomThread(arr, pos, param));
            pos += step;
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Sum by threads: " + CustomThread.getSum());
    }


}
