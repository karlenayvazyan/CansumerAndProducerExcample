import java.util.Random;

public class ProducerConsumerImpl {

    private Integer queue;
    private final Random random;

    public ProducerConsumerImpl() {
        this.random = new Random();
    }

    public void put() throws InterruptedException {
        while (queue != null) {
            synchronized (this) {
                wait();
            }
        }
        queue = random.nextInt();
        System.out.println("Add " + queue);
        synchronized (this) {
            notify();
        }
    }

    public void get() throws InterruptedException {
        while (queue == null) {
            synchronized (this) {
                wait();
            }
        }
        System.out.println("Read " + queue);
        queue = null;
        synchronized (this) {
            notify();
        }
    }
}
