package code.shubham.commons.exceptions;

import code.shubham.commons.constants.CommonConstants;

public class CommandNotFoundException extends Exception {

    private final String name;

    public CommandNotFoundException(final String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return String.format(CommonConstants.COMMAND_NOT_FOUND_EXCEPTION_MESSAGE, this.name);
    }
}