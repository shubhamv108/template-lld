package code.shubham.commons.clients;

import code.shubham.commons.interations.commands.CommandFactory;

import java.io.BufferedReader;

public class CLIClient extends Client {
    public CLIClient(
            final BufferedReader inputReader,
            final CommandFactory commandFactory) {
        super(inputReader, commandFactory);
    }

    @Override
    protected void instruction() {
        System.out.print("> ");
    }

}
