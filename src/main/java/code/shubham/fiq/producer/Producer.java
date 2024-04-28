package code.shubham.fiq.producer;

import code.shubham.fiq.models.Message;
import code.shubham.fiq.services.Queue;
import code.shubham.fiq.services.QueueFactory;

import java.util.List;

public class Producer implements Runnable {
    private final String queueName;
    private final List<Message> messages;

    public Producer(
            final String queueName,
            final Message messages) {
        this(queueName, List.of(messages));
    }

    public Producer(
            final String queueName,
            final List<Message> messages) {
        this.queueName = queueName;
        this.messages = messages;
    }

    @Override
    public void run() {
        final Queue queue = QueueFactory.getFactory().getQueue(this.queueName);
        this.messages.forEach(message -> this.send(queue, message));
    }

    private void send(final Queue queue, final Message message) {
        try {
            queue.offer(message);
            System.out.printf("ACK: %s%n", message);
        } catch (Exception exception) {
            System.out.printf("NACK: %s%n", message);
            System.out.println(exception.getMessage());
        }
    }
}
