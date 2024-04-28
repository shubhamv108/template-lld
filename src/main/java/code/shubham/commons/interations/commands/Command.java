package code.shubham.commons.interations.commands;

import java.security.InvalidParameterException;

public interface Command {

    String helpText();

    void execute(String[] params);

}