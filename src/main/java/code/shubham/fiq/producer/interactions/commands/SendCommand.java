package code.shubham.fiq.producer.interactions.commands;

import code.shubham.commons.interations.commands.Command;
import code.shubham.fiq.models.Message;
import code.shubham.fiq.producer.handlers.ProducerCommandHandler;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SendCommand implements Command {
    private final ProducerCommandHandler commandHandler;

    public SendCommand(final ProducerCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public String helpText() {
        return "send <queueName> <key>:<message>:<processing_time>";
    }

    @Override
    public void execute(final String[] params) {
        if (params.length < 2) {
            throw new InvalidParameterException("Expected three parameters <queueName> and <key>:<message>:<processing_time>");
        }

        final String message = Arrays.stream(params)
                .skip(1)
                .collect(Collectors.joining(" "));
        this.commandHandler.send(params[0], Message.of(message));
    }
}