import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int step = 0;
        boolean isPrime = true;

        if (input < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        } else {
            for (int i = 2; i * i <= input; i++) {
                step++;
                if (input % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println(isPrime + " " + step);
    }
}