package code.shubham.commons.interations.commands;

import code.shubham.commons.exceptions.CommandNotFoundException;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();

    public void executeCommand(
            final String name,
            final String[] params)
            throws CommandNotFoundException, InvalidParameterException {
        if (this.commands.containsKey(name)) {
            this.commands.get(name).execute(params);
        } else {
            throw new CommandNotFoundException(name);
        }
    }

    public void addCommand(final String name, final Command command) {
        this.commands.put(name, command);
    }

    public void listCommandHelp() {
        this.commands
                .keySet()
                .stream()
                .map(command -> this.commands.get(command).helpText())
                .forEach(System.out::println);
    }

    public Map<String, Command> getCommands() {
        return this.commands;
    }
}
