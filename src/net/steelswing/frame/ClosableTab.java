/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import net.steelswing.IconManager;
import net.steelswing.tree.FileNode;
import net.steelswing.util.SwingUtils;

/**
 * File: ClosableTab.java
 * Created on 16.03.2023, 14:31:30
 *
 * @author LWJGL2
 */
public class ClosableTab extends JPanel {

    private FileNode fileNode;
    private JLabel tabTitle;
    private JLabel closeButton = new JLabel(IconManager.ICONS.ICON_CLOSE_1_16);

    public ClosableTab(FileNode fileNode, javax.swing.JTabbedPane tabbedPane, String title, ImageIcon icon, Runnable onCloseTabAction, Runnable onCloseAllClicked, Runnable onCloseOtherClicked) {
        super(new GridBagLayout());
        this.fileNode = fileNode;
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
        this.setOpaque(false);
        this.tabTitle = new JLabel(title);
        this.createTab(iconLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    JPopupMenu menu = new JPopupMenu();
                    {
                        final JMenuItem item = new JMenuItem("Close");
                        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
                        item.addActionListener((ActionEvent event) -> {
                            try {
                                if (onCloseTabAction != null) {
                                    onCloseTabAction.run();
                                }
                            } catch (Exception ex) {
                                SwingUtils.showErrorDialog("Failed to close tab", ex);
                            }
                        });
                        menu.add(item);
                    }
                    {
                        final JMenuItem item = new JMenuItem("Close all");
                        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
                        item.addActionListener((ActionEvent event) -> {
                            try {
                                if (onCloseAllClicked != null) {
                                    onCloseAllClicked.run();
                                }
                            } catch (Exception ex) {
                                SwingUtils.showErrorDialog("Failed to close tab", ex);
                            }
                        });
                        menu.add(item);
                    }
                    {
                        final JMenuItem item = new JMenuItem("Close other");
                        item.addActionListener((ActionEvent event) -> {
                            try {
                                if (onCloseOtherClicked != null) {
                                    onCloseOtherClicked.run();
                                }
                            } catch (Exception ex) {
                                SwingUtils.showErrorDialog("Failed to close tab", ex);
                            }
                        });
                        menu.add(item);
                    }

                    SwingUtils.showPopupMenu(menu, ClosableTab.this, e.getX(), e.getY());
                } else {
                    tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(title));
                }
            }

        });

        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setIcon(IconManager.ICONS.ICON_CLOSE_2_16);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setIcon(IconManager.ICONS.ICON_CLOSE_1_16);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (onCloseTabAction != null) {
                        onCloseTabAction.run();
                    }
                } catch (Exception ex) {
                    SwingUtils.showErrorDialog("Failed to close tab", ex);
                }
            }
        });
    }

    public void createTab(JLabel iconLabel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(iconLabel, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.anchor = GridBagConstraints.EAST;
        add(tabTitle);

        gbc.gridx++;
        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.anchor = GridBagConstraints.EAST;
        add(closeButton);
    }

    public FileNode getFileNode() {
        return fileNode;
    }

    public JLabel getTabTitle() {
        return tabTitle;
    }

    public JLabel getCloseButton() {
        return closeButton;
    }

}
