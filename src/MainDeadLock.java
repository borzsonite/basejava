public class MainDeadLock {
    static int counter;

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        Thread thread1 = new Thread(() -> {
            deadLockExecutor(lockA, lockB);
        });

        Thread thread2 = new Thread(() -> {
            deadLockExecutor(lockB, lockA);
        });

        thread1.start();
        thread2.start();
    }

    static void deadLockExecutor(String lockA, String lockB) {
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

