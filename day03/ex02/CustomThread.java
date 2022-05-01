public class CustomThread extends Thread {
    private int posA;
    private int posZ;
    private long sum;
    private static long sumDone = 0;

    public CustomThread(int[ ]arr, int posA, int posZ) {
        this.posA = posA;
        this.posZ = posZ;
        sum = 0;
        for (int num: arr) {
            sum += num;
        }
    }

    public static synchronized void calculate(long sum, int posA, int posZ) {
        System.out.print(Thread.currentThread().getName() + ": ");
        System.out.print("from " + posA + " to " + posZ + " sum is " + sum);
        System.out.println();
        sumDone += sum;
    }

    @Override
    public void run() {
        calculate(sum, posA, posZ);
    }

    public static long getSum() {
        return sumDone;
    }
}
