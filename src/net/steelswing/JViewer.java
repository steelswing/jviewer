/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing;

import com.formdev.flatlaf.FlatDarkLaf;
import java.util.logging.Logger;
import javax.swing.UIManager;
import net.steelswing.frame.MainFrame;
import net.steelswing.util.SwingUtils;

/**
 *
 * @author LWJGL2
 */
public class JViewer {

    private static final Logger LOG = Logger.getLogger(JViewer.class.getName());

    private static JViewer instance;

    private final MainFrame mainFrame;

    public JViewer() throws Exception {
        instance = this;
        mainFrame = new MainFrame(this);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public static JViewer getInstance() {
        return instance;
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(FlatDarkLaf.class.getName());
        } catch (Throwable e) {
            SwingUtils.showErrorDialog("Failed to set theme", e);
        }
        initLogger();

        LOG.info("Init JViewer");
        JViewer jViewer = new JViewer();
    }

    protected static void initLogger() {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
//        try {
//            // Remove all the default handlers (usually just one console handler)
//            Logger rootLogger = Logger.getLogger("");
//            Handler[] rootHandlers = rootLogger.getHandlers();
//            for (Handler handler : rootHandlers) {
//                rootLogger.removeHandler(handler);
//            }
//
//            // Add our own handler
//            ConsoleHandler handler = new ConsoleHandler() {
//                {
//                    setOutputStream(System.out);
//                }
//            };
//            handler.setLevel(Level.INFO);
//            handler.setFormatter(new SimpleFormatter());
//            rootLogger.addHandler(handler);
//            rootLogger.setLevel(Level.INFO);
//        } catch (Throwable e) {
//            System.out.println("Failed to init logger!");
//            e.printStackTrace();
//        }
    }

}
