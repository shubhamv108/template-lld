package code.shubham.fiq.interactions;

import code.shubham.fiq.producer.handlers.ProducerCommandHandler;
import code.shubham.commons.interations.commands.CommandFactory;
import code.shubham.fiq.producer.interactions.commands.SendCommand;

public class CommandFactoryProvider {

    public CommandFactory getProducerCommandFactory(final ProducerCommandHandler commandHandler) {
        final CommandFactory commandFactory = new CommandFactory();

        commandFactory.addCommand("send", new SendCommand(commandHandler));

        return commandFactory;
    }

    public CommandFactory getConsumerCommandFactory(final ProducerCommandHandler commandHandler) {
        final CommandFactory commandFactory = new CommandFactory();

        return commandFactory;
    }

    public static CommandFactoryProvider getProvider() {
        return SingletonHolder.INSTANCE;
    }

    public static final class SingletonHolder {
        private static final CommandFactoryProvider INSTANCE = new CommandFactoryProvider();
    }
}
