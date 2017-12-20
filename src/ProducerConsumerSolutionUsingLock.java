public class ProducerConsumerSolutionUsingLock {

    public static void main(String[] args) {
        ProducerConsumerImpl producerConsumer = new ProducerConsumerImpl();

        Producer producer = new Producer(producerConsumer);
        Consumer consumer = new Consumer(producerConsumer);
        consumer.start();
        producer.start();
    }
}
