
public class Program {
    public static class EggThread extends Thread {
        private final int count;

        public EggThread(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                System.out.println("Egg");
            }
        }
    }

    public static class HenThread extends Thread {
        private final int count;

        public HenThread(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                System.out.println("Hen");
            }
        }
    }

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
            EggThread egg = new EggThread(count);
            HenThread hen = new HenThread(count);
            egg.start();
            hen.start();
            egg.join();
            hen.join();
            for (int i = 0; i < count; i++) {
                System.out.println("Human");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }

    }
}
