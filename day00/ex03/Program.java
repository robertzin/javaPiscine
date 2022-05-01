import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String input;
        int weeks = 0;
        long allMarks = 0;

        Scanner scanner = new Scanner(System.in);

        while (!((input = scanner.nextLine()).equals("42")) && weeks < 18) {
            weeks++;
            if (!input.equals("Week " + weeks)) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            } else {
                allMarks = addMarks(getMarksOfWeek(scanner), allMarks);
            }
        }
        drawGraph(weeks, allMarks);
    }

    public static int getMarksOfWeek(Scanner scanner) {
        int marks = 0;

        for (int i = 0; i < 5; i++) {
            marks *= 10;
            int tmp = scanner.nextInt();
            if (tmp < 1) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            marks += tmp;
        }
        scanner.nextLine();
        return findMinimalMark(marks);
    }

    public static int findMinimalMark(int num) {
        int min = 9;

        while (num != 0) {
            int tmp = num % 10;
            min = min > tmp % 10 ? tmp : min;
            num /= 10;
        }
        return min;
    }

    public static long addMarks(int num, long marks) {
        long cpyMarks = marks;
        long tmp = num;

        if (cpyMarks != 0) {
            while (cpyMarks != 0) {
                cpyMarks /= 10;
                tmp *= 10;
            }
        }
        return(tmp + marks);
    }

    public static void drawGraph(int weeks, long marks) {
        for(int i = 1; i <= weeks; i++) {
            System.out.print("Week " + i + " ");
            long tmp = marks % 10;
            for(int j = 0; j < tmp; j++) {
                System.out.print("=");
            }
            marks /= 10;
            System.out.println(">");
        }
    }
}