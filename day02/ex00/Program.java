import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Map<String, String> signatures = readSignatures();

        StringBuilder str = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equals("42")) {
           str.setLength(0);
            try (FileInputStream inputStream = new FileInputStream(input);
                 FileOutputStream outputStream = new FileOutputStream("/Users/robert_zin/Documents/42Cursus/_Piscine_Java/day02/ex00/result.txt", true)) {
                for (int i = 0; inputStream.available() > 0 && i < 8; i++) {
                    str.append(String.format("%02X", inputStream.read()));
                }

                String inputSignature = str.toString();
                boolean find = findIt(inputStream, outputStream, inputSignature, signatures);
                if (find) {
                    System.out.println("PROCESSED");
                }
                else if (!find)
                    System.out.println("UNDEFINED");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static boolean findIt(FileInputStream is, FileOutputStream os, String inputSignature, Map<String, String> signatures) {
        String findSignature;

        for (String key: signatures.keySet()) {
            String toCompare = inputSignature;
            if (key.length() < inputSignature.length())
                toCompare = inputSignature.substring(0, key.length());
            if (toCompare.startsWith(key)) {
                findSignature = signatures.get(key);
                try {
                    os.write(findSignature.getBytes());
                    os.write('\n');
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return true;
            }
        }
        return false;
    }

    public static Map<String, String> readSignatures() {
        Map<String, String> signatures = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        try (FileInputStream inputStream = new FileInputStream("/Users/robert_zin/Documents/42Cursus/_Piscine_Java/day02/ex00/signatures.txt")) {
            int rd;
            while ((rd = inputStream.read()) != -1) {
                if (inputStream.available() < 0 || (char)rd == '\n') {
                    String[] line = stringBuilder.toString().split(", ");
                    signatures.put(line[1].trim().replaceAll("\\s+",""), line[0]);
                    stringBuilder.setLength(0);
                    continue;
                }
                stringBuilder.append((char) rd);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return signatures;
    }
}
