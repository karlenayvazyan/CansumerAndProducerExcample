public class Consumer extends Thread {

    private final ProducerConsumerImpl producerConsumer;

    public Consumer(ProducerConsumerImpl producerConsumer) {
        System.out.println("Consumer");
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                producerConsumer.get();
                sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
