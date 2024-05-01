package code.shubham.commons.clients;

import code.shubham.commons.exceptions.CommandNotFoundException;
import code.shubham.commons.interations.commands.CommandFactory;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Arrays;

public class Client implements AutoCloseable {
    private final BufferedReader inputReader;
    private final CommandFactory commandFactory;

    public Client(
            final BufferedReader inputReader,
            final CommandFactory commandFactory) {
        this.inputReader = inputReader;
        this.commandFactory = commandFactory;
    }

    public void handleInput() throws IOException, OperationNotSupportedException {
        try {
            while (true) {
                this.instruction();
                String inputLine = this.inputReader.readLine();
                if (inputLine == null) {
                    break;
                }

                inputLine = inputLine.trim();
                if (inputLine.isEmpty()) {
                    continue;
                }

                if (inputLine.equalsIgnoreCase("exit")) {
                    break;
                }

                this.processInputLine(inputLine);
            }
        } finally {
            this.inputReader.close();
        }
    }

    public void processInputLine(final String inputLine) {
        final String[] inputChunks = inputLine.split(" ");

        final String command = inputChunks[0];
        final String[] params = Arrays.copyOfRange(inputChunks, 1, inputChunks.length);

        try {
            this.commandFactory.executeCommand(command, params);
        } catch (final CommandNotFoundException | InvalidParameterException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    protected void instruction() {}

    @Override
    public void close() throws Exception {
        this.inputReader.close();
    }
}
