package code.shubham.app.handlers;

public class AppCommandHandler {

    private AppCommandHandler() {}

    public static AppCommandHandler getHandler() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final AppCommandHandler INSTANCE = new AppCommandHandler();
    }
}
