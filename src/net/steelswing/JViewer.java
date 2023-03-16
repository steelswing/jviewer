/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import net.steelswing.frame.MainFrame;
import net.steelswing.util.SwingUtils;

/**
 *
 * @author LWJGL2
 */
public class JViewer {

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

        JViewer jViewer = new JViewer();
    }


}
