package code.shubham.app;

import code.shubham.app.handlers.AppCommandHandler;
import code.shubham.app.interactions.AppCommandFactoryProvider;
import code.shubham.commons.clients.Client;
import code.shubham.commons.clients.ClientFactory;
import code.shubham.commons.interations.commands.CommandFactory;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppMain {

    public static void main(String[] args) {
        final AppCommandHandler commandHandler = AppCommandHandler.getHandler();
        final CommandFactory commandFactory = AppCommandFactoryProvider
                .getProvider()
                .getCommandFactory(commandHandler);

        try {
            AppMain.initCLIClient(args, commandFactory);
        } catch (final Exception ex) {
            System.out.println("Something went wrong. Please try again!");
        }
    }

    public static void initCLIClient(
            final String[] args,
            final CommandFactory commandFactory)
            throws OperationNotSupportedException, IOException {
        final Client client = ClientFactory.buildClient(args, commandFactory);
        client.handleInput();
    }

    private static String[] getStaticInput() {
        final List<String> inputs = new ArrayList<>();
        return inputs.toArray(String[]::new);
    }

}