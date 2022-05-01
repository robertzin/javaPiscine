import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input;
        int count = 0;
        while ((input = scanner.nextInt()) != 42) {
            if (isPrime(convertToNum(input)))
                count++;
        }
        System.out.println("Count of coffee-request - " + count);
    }

    public static int convertToNum(int someInt) {
        int res = 0;

        while (someInt != 0) {
            int tmp = someInt % 10;
            res += tmp;
            someInt /= 10;
        }
        return res;
    }

    public static boolean isPrime(int input) {
        boolean isPrime = true;

        if (input < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        } else {
            for (int i = 2; i * i <= input; i++) {
                if (input % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;
    }
}