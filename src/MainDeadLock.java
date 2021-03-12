import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainDeadLock {

    public static void main(String[] args) throws InterruptedException {
        DeadLockRunner dlr = new DeadLockRunner();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dlr.processThis();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                dlr.processThat();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(dlr.getCounter());
    }

}

    class DeadLockRunner {
        private int counter;
        private Lock lockA = new ReentrantLock();
        private Lock lockB = new ReentrantLock();

        public void processThis() {
            lockA.lock();
            lockB.lock();
            for(int i=0; i<10000; i++) {
                counter++;
            }
            lockA.unlock();
            lockB.unlock();
        }

        public void processThat() {
            lockB.lock();
            lockA.lock();
            for(int i=0; i<10000; i++) {
                counter++;
            }
            lockA.unlock();
            lockB.unlock();
        }

        public int getCounter() {
            return counter;
        }
    }

