package code.shubham.commons.clients;

import code.shubham.commons.interations.commands.CommandFactory;

import java.io.BufferedReader;

public class FileClient extends Client {

    public FileClient(
            final BufferedReader inputReader,
            final CommandFactory commandFactory) {
        super(inputReader, commandFactory);
    }

}