package code.shubham.app.interactions;

import code.shubham.app.handlers.AppCommandHandler;
import code.shubham.commons.interations.commands.CommandFactory;

public class AppCommandFactoryProvider {

    private AppCommandFactoryProvider() {}

    public CommandFactory getCommandFactory(final AppCommandHandler commandHandler) {
        final CommandFactory commandFactory = new CommandFactory();
        return commandFactory;
    }

    public static AppCommandFactoryProvider getProvider() {
        return SingletonHolder.INSTANCE;
    }

    public static final class SingletonHolder {
        private static final AppCommandFactoryProvider INSTANCE = new AppCommandFactoryProvider();
    }
}
