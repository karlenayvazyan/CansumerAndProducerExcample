import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerImpl {

    private final int capacity = 2;
    private final Lock aLock;
    private Integer queue;
    private final Random random;
    private final Condition bufferNotFull;
    private final Condition bufferNotEmpty;

    public ProducerConsumerImpl() {
        this.aLock = new ReentrantLock();
        this.random = new Random();
        this.bufferNotFull = aLock.newCondition();
        bufferNotEmpty = aLock.newCondition();
    }

    public void put() throws InterruptedException {
        aLock.lock();
        try {
            while (queue != null) {
                System.out.println("Full");
                bufferNotEmpty.await();
            }
            int number = random.nextInt();
            queue = number;
            System.out.printf("%s added %d into queue %n", Thread.currentThread().getName(), number);
            bufferNotFull.signalAll();
        } finally {
            aLock.unlock();
        }
    }

    public void get() throws InterruptedException {
        aLock.lock();
        try {
            while (queue == null) {
                System.out.println("Empty");
                bufferNotFull.await();
            }
            final Integer value = queue;
            queue = null;
            if (value != null) {
                System.out.printf("%s consumed %d from queue %n", Thread.currentThread().getName(), value);
                bufferNotEmpty.signalAll();
            }
        } finally {
            aLock.unlock();
        }
    }
}
