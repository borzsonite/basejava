import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainDeadLock {
    static int counter;

    public static void main(String[] args) throws InterruptedException {
        Lock lockA = new ReentrantLock();
        Lock lockB = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                deadLockExecutor(lockA, lockB);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                deadLockExecutor(lockB, lockA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter);
    }

    static void deadLockExecutor(Lock lockA, Lock lockB) throws InterruptedException {
        lockA.lock();
        Thread.sleep(500);
        lockB.lock();

        for(int i=0; i<10000; i++) {
            counter++;
        }

        lockA.unlock();
        lockB.unlock();
    }

}

