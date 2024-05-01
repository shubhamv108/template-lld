package code.shubham.commons.clients;

import code.shubham.commons.interations.commands.CommandFactory;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;

public class DefaultClient extends Client {
    public DefaultClient(final CommandFactory commandFactory) {
        super(null, commandFactory);
    }

    @Override
    public void handleInput() throws IOException, OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
