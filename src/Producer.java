public class Producer extends Thread {

    private final ProducerConsumerImpl producerConsumer;

    public Producer(ProducerConsumerImpl producerConsumer) {
        System.out.println("Producer");
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                producerConsumer.put();
                sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
