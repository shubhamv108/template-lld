package code.shubham.fiq.services;

import code.shubham.fiq.models.Message;

import java.util.List;

public interface Queue {
    void offer(Message message) throws Exception;

    Message poll();
}
