/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.util;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import net.steelswing.IconManager;
import net.steelswing.frame.dialog.TextPanel;

/**
 *
 * @author MrJavaCoder
 */
public class SwingUtils {

    public static JPopupMenu menu;
    private static boolean error = false, cursorVisible = true;

    private static Toolkit t = Toolkit.getDefaultToolkit();
    private static Image i = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    private static Cursor noCursor = t.createCustomCursor(i, new Point(0, 0), "none");

    private static boolean aga;

    private static JFrame frame;

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        SwingUtils.frame = frame;
    }


    public static void showPopupMenu(JPopupMenu menu, Component component, int x, int y) {
        try {
//            if (SwingUtils.menu != null) {
//                SwingUtils.menu.setVisible(false);
//            }
//
//            SwingUtils.menu = menu;
            menu.show(component, x, y);
            menu.putClientProperty("JPopupFactory.startHidden", true);
//            menu.setUI(new  com.github.weisj.darklaf.ui.popupmenu.DarkPopupMenuUI());
//                    System.out.println(menu.getUI());
//            menu.setInvoker(null);
//            menu.setInvoker(menu);
//            menu.getUI().uninstallUI(menu);
            for (Component comp : menu.getComponents()) {
                if (comp instanceof JMenu) {
                    JMenu jm = (JMenu) comp;
//                    DarkMenuUI ui = new com.github.weisj.darklaf.ui.menu.DarkMenuUI();
//                    jm.setUI(ui);
//                    ui.installUI(jm);
//                    jm.putClientProperty("JPopupFactory.startHidden", true);
//                    jm.setUI(new Metal() {
//                    });
//                    System.out.println(jm.getU());
//                    jm.getUI().uninstallUI(jm);
//                    System.out.println(jm.isVisible());
//                    jm.addComponentListener(new ComponentAdapter() {
//                        @Override
//                        public void componentHidden(ComponentEvent e) {
//                            System.out.println("hide");
//                        }
//
//                        @Override
//                        public void componentShown(ComponentEvent e) {
//                            System.out.println("show");
//                        }
//                    
//                    });
//                    jm.getPopupMenu().setInvoker(null);
                }
            }
        } catch (Exception e) {
        }
    }

    public static Result showConfirmDialog(String title, ImageIcon icon, String message) {
        return Result.get(JOptionPane.showConfirmDialog(frame, message, title, 0, 0, icon));
    }

    public static Result showErrorDialog(String title, String message) {
        return Result.get(JOptionPane.showConfirmDialog(frame, new TextPanel().setText(message), title, 2, 0, IconManager.TOOL.WARN_32ICON));
    }

    public static String showInputDialog(String title, String message) {
        return (String) JOptionPane.showInputDialog(frame, title, message, 1, IconManager.TOOL.QUEST_32ICON, null, null);
    }

    public static String showInputDialog(String title, ImageIcon icon, String message) {
        return (String) JOptionPane.showInputDialog(frame, title, message, 1, icon, null, null);
    }

    public static Result showErrorDialog(String title, Throwable ex) {
        ex.printStackTrace();
        StringBuilder builder = new StringBuilder();
        builder.append("STACKTRACE: ").append("\n\n").append(ex.getClass()).append(": ").append(ex.getMessage()).append("\n");
        builder.append("\n");

        for (StackTraceElement e : ex.getStackTrace()) {
            builder.append("\t").append(e).append("\n");
        }
        error = true;
        Result r = Result.get(JOptionPane.showConfirmDialog(frame, new TextPanel().setText(builder.toString()), title, 2, 0, IconManager.TOOL.WARN_32ICON));
        error = false;
        return r;
    }

    public static Result showErrorDialog(String title, String message, Exception ex) {
        ex.printStackTrace();
        StringBuilder builder = new StringBuilder();
        builder.append(message).append("\n");
        builder.append("STACKTRACE: ").append("\n\n").append(ex.getClass()).append(": ").append(ex.getMessage()).append("\n");
        builder.append("\n");

        for (StackTraceElement e : ex.getStackTrace()) {
            builder.append("\t").append(e).append("\n");
        }
        error = true;
        Result r = Result.get(JOptionPane.showConfirmDialog(frame, new TextPanel().setText(builder.toString()), title, 2, 0, IconManager.TOOL.WARN_32ICON));
        error = false;
        return r;
    }

    public static Result showConfirmDialog(JPanel panel, String title, String message) {
        return Result.get(JOptionPane.showConfirmDialog(frame, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE));
    }

    public static enum Result {
        YES(0), NO(1), CANCEL(2);

        public static final Result[] VALUES = values();
        public final int result;

        private Result(int id) {
            result = id;
        }

        public static Result get(int id) {
            for (Result value : VALUES) {
                if (value.result == id) {
                    return value;
                }
            }
            return CANCEL;
        }
    }
}
