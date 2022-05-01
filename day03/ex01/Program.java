import java.util.LinkedList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        if (args.length < 1)
            System.err.println("Usage: \"java Pogram --count=[num]\"");
        else if (!args[0].startsWith("--count="))
            System.err.println("Usage: \"java Pogram --count=[num]\"");
        else if (Integer.parseInt(args[0].substring(8)) <= 0)
            System.err.println("Error! [num] should be greater than 1");

        String num = args[0].substring(8);


        try {
            int count = Integer.parseInt(num);

            ChickenThread egg = new ChickenThread("Egg", count);
            ChickenThread hen = new ChickenThread("Hen", count);

            egg.start();
            hen.start();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }

    }
}
