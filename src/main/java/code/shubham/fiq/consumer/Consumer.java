package code.shubham.fiq.consumer;

import code.shubham.fiq.models.Message;
import code.shubham.fiq.services.FileQueue;
import code.shubham.fiq.services.Queue;
import code.shubham.fiq.services.QueueFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Consumer {
    private final String queueName;
    private final int concurrencyFactor;

    public Consumer(
            final String queueName,
            final int concurrencyFactor) {
        this.queueName = queueName;
        this.concurrencyFactor = concurrencyFactor;
    }

    public void consume() {
        final ExecutorService executor = Executors.newFixedThreadPool(
                this.concurrencyFactor);
        final Queue queue = QueueFactory.getFactory().getQueue(this.queueName);
        for (int i = 0; i < this.concurrencyFactor; ++i) {
            executor.submit(() -> {
                while (true) {
                    final Message message = queue.poll();
                    this.processMessage(message);
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    private void processMessage(final Message message) {
        System.out.println("Processing message: " + message);
        try {
            Thread.sleep(message.getProcessingTimeInSeconds() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Processed message: " + message);
    }
}
