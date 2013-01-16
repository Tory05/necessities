package kdx7214.necessities;


import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.base.Optional;

import cpw.mods.fml.common.FMLLog;

public enum LogHelper {
    INSTANCE;

    public static void fine(String format, Object... args) {
        INSTANCE.log(Level.FINE, format, args);
    }

    public static void finer(String format, Object... args) {
        INSTANCE.log(Level.FINER, format, args);
    }

    public static void finest(String format, Object... data) {
        INSTANCE.log(Level.FINEST, format, data);
    }

    public static void info(String format, Object... args) {
        INSTANCE.log(Level.INFO, format, args);
    }

    public static void log(Level level, Throwable exception, String format, Object... args) {
        INSTANCE.getLogger().log(level, String.format(format, args), exception);
    }

    public static void severe(String format, Object... args) {
        INSTANCE.log(Level.SEVERE, format, args);
    }

    public static void warning(String format, Object... args) {
        INSTANCE.log(Level.WARNING, format, args);
    }

    private Optional<Logger> logger = Optional.absent();

    private Logger getLogger() {
        if (!logger.isPresent()) init();

        return logger.get();
    }

    private void init() {
        if (logger.isPresent()) return;

        logger = Optional.of(Logger.getLogger("Necessities"));
        logger.get().setParent(FMLLog.getLogger());
    }

    private void log(Level level, String format, Object... data) {
        getLogger().log(level, String.format(format, data));
    }
}