package code.shubham.commons.clients;

import code.shubham.commons.interations.commands.CommandFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;

public class ClientFactory {

    public static Client buildClient(
            final String[] args,
            final CommandFactory commandFactory)
            throws FileNotFoundException {
        if (args.length > 1) {
            return new Client(new BufferedReader(new StringReader(String.join("\n", Arrays.asList(args)))), commandFactory);
        } else if (args.length == 1) {
            return new FileClient(new BufferedReader(new FileReader(args[0])), commandFactory);
        } else {
            return new CLIClient(new BufferedReader(new InputStreamReader(System.in)), commandFactory);
        }
    }

    public static Client defaultClient(
            final CommandFactory commandFactory) {
        return new DefaultClient(commandFactory);
    }
}