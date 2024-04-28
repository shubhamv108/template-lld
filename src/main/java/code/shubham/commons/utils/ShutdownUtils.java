package code.shubham.commons.utils;

import java.util.function.Function;

public class ShutdownUtils {

    public static void defer(final Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(new Thread(runnable));
    }

}
