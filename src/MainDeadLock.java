public class MainDeadLock {
    static int counter;

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(() -> {
            executeDeadlock(lockA, lockB);
        }).start();

        new Thread(() -> {
            executeDeadlock(lockB, lockA);
        }).start();
    }

    static void executeDeadlock(String lockA, String lockB) {
        System.out.println("Thread " + Thread.currentThread().getName() + " is trying to get " + lockA + " inside outer block");
        synchronized (lockA) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " got " + lockA + " inside outer block");

            System.out.println("Thread " + Thread.currentThread().getName() + " is trying to get " + lockB + " inside inner block");
            synchronized (lockB) {
                System.out.println("Thread " + Thread.currentThread().getName() + " got " + lockB + " inside inner block");
            }
        }
    }

}

