public class ChickenThread extends Thread {
    private final String text;
    private final int count;
    private final static Object lock = new Object();
    private final int threadId;
    private static int numOfThreads;
    private static int currentThreadId;

    public ChickenThread(String text, int count) {
        this.text = text;
        this.count = count;
        this.threadId = numOfThreads++;
    }

    public void run() {
        for(int i = 0; i < count; i++) {

            synchronized (lock) {
                while (currentThreadId != threadId) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(text);
                currentThreadId = threadId == numOfThreads - 1 ? 0 : threadId + 1;
                lock.notifyAll();
            }
        }
    }
}
