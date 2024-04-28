package code.shubham.fiq.producer.handlers;

import code.shubham.fiq.models.Message;
import code.shubham.fiq.producer.Producer;
import code.shubham.fiq.services.Queue;
import code.shubham.fiq.services.QueueFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerCommandHandler {

    private ExecutorService executor = Executors.newFixedThreadPool(10);

    private ProducerCommandHandler() {}

    public void send(final String queueName, final Message message) {
        this.executor.submit(new Producer(queueName, message));
    }

    public static ProducerCommandHandler getHandler() {
        return SingletonHolder.INSTANCE;
    }

    public static final class SingletonHolder {
        private static final ProducerCommandHandler INSTANCE = new ProducerCommandHandler();
    }

}
