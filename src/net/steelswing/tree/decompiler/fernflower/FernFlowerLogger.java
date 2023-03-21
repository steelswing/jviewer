/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree.decompiler.fernflower;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;
import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger.Severity;

/**
 * File: FernFlowerLogger.java
 * Created on 18.03.2023, 20:29:01
 *
 * @author LWJGL2
 */
public class FernFlowerLogger extends IFernflowerLogger {

    private static final Logger LOG = Logger.getLogger(FernFlowerLogger.class.getName());

    @Override
    public void writeMessage(String message, Severity severity) {
        if (!accepts(severity)) {
            return;
        }
        switch (severity) {
            case TRACE:
                LOG.info(message);
                break;
            case INFO:
                LOG.info(message);
                break;
            case WARN:
                LOG.warning(message);
                break;
            case ERROR:
                LOG.severe(message);
                break;
            default:
                break;
        }
    }

    @Override
    public void writeMessage(String message, Severity severity, Throwable throwable) {
        writeMessage(message, severity);
        LOG.log(Level.INFO, message, throwable);
    }
}
