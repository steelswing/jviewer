/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import net.steelswing.IconManager;
import net.steelswing.JViewer;
import net.steelswing.frame.view.SyntaxPanel;
import net.steelswing.tree.filesystem.FileSystem;
import net.steelswing.tree.filesystem.OSFileSystem;
import net.steelswing.tree.filesystem.ZipFileSystem;

/**
 * File: MainFrame.java
 * Created on 24.08.2022, 11:20:16
 *
 * @author LWJGL2
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    protected final JViewer main;
    protected MainPanel mainPanel;
    protected JPanel currentPanel;

    protected JMenuItem menuBarCloseFile;

    protected JMenuItem menuBarCopy;
    protected JMenuItem menuBarSelectAll;
    protected JMenuItem menuBarFind;

    public MainFrame(JViewer main) throws Exception {
        this.main = main;
        this.setTitle("JViewer");
        this.setLayout(new BorderLayout());
        this.initMenuBar();
        this.showStartScreen();

        try {
            this.setIconImage(IconManager.ICONS.ICON.getImage());
        } catch (Throwable e) {
            System.err.println("Failed to set icon!");
            e.printStackTrace();
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void showStartScreen() {
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        this.add(currentPanel = new DragAndDropPanel(this));
        this.revalidate();
        this.setUnlockMenuBar(false);
    }

    public void openFiles(List<File> files) {
        if (files == null) {
            showStartScreen();
            return;
        }
        FileSystem fileSystem = null;

        for (File file : files) {
            if (file.isDirectory()) {
                fileSystem = new OSFileSystem(file);
            } else {
                String name = file.getName().toLowerCase();
                if (name.endsWith(".zip") || name.endsWith(".jar")) {
                    try {
                        fileSystem = new ZipFileSystem(file);
                        // TODO
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    File parent = file.getParentFile();
                    if (parent != null && parent.isDirectory()) {
                        fileSystem = new OSFileSystem(file.getParentFile());
                    }
                }
            }
        }

        if (fileSystem == null) {
            showStartScreen();
            return;
        }
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        this.mainPanel = new MainPanel(this, fileSystem);
        this.currentPanel = mainPanel;
        this.add(mainPanel);
        this.revalidate();
        this.setUnlockMenuBar(true);
    }

    public JViewer getMain() {
        return main;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    protected void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        {
            {
                JMenu menu = new JMenu("File");
                {
                    JMenuItem item = new JMenuItem("Open File...", IconManager.FOLDER.FOLDER_OPEN_16ICON);
                    item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
                    item.addActionListener((e) -> openProjectActionPerformed(e));
                    menu.add(item);
                }
                menu.addSeparator();
                {
                    menuBarCloseFile = new JMenuItem("Close");
                    menuBarCloseFile.addActionListener((e) -> openFiles(null));
                    menu.add(menuBarCloseFile);
                }
                menuBar.add(menu);
            }
            {
                JMenu menuBarEdit = new JMenu("Edit");
                {
                    {
                        menuBarCopy = new JMenuItem("Copy", IconManager.TOOL.SAVE_16ICON);
                        menuBarCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
                        menuBarCopy.addActionListener((e) -> copyCurrentData(e));
                        menuBarEdit.add(menuBarCopy);
                    }
                    {
                        menuBarSelectAll = new JMenuItem("Select All");
                        menuBarSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
                        menuBarSelectAll.addActionListener((e) -> selectAllCurrentData(e));
                        menuBarEdit.add(menuBarSelectAll);
                    }
                    menuBarEdit.addSeparator();
                    {
                        menuBarFind = new JMenuItem("Find...", IconManager.ICONS.ICON_MAGNIFIER_16);
                        menuBarFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
                        menuBarFind.addActionListener((e) -> findAll(e));
                        menuBarEdit.add(menuBarFind);
                    }
                    menuBarEdit.addSeparator();
                    {
                        JMenuItem menu = new JMenuItem("Preferences", IconManager.ICONS.EDIT_ICON16);
                        menu.addActionListener((e) -> openPreferences(e));
                        menuBarEdit.add(menu);
                    }
                }
                menuBar.add(menuBarEdit);
            }
        }
        this.setJMenuBar(menuBar);
    }

    protected void setUnlockMenuBar(boolean unlock) {
        this.menuBarCloseFile.setEnabled(unlock);
        this.menuBarCopy.setEnabled(unlock);
        this.menuBarSelectAll.setEnabled(unlock);
        this.menuBarFind.setEnabled(unlock);
    }

    private void openProjectActionPerformed(ActionEvent evt) {
        File file = new File(".");
//        if (main.getJsonConfig().has("lastFolder")) {
//            file = new File(main.getJsonConfig().getString("lastFolder"));
//        }
        JFileChooser chooser = new JFileChooser(file);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showDialog(this, "Open File...");

        if (chooser.getSelectedFile() != null) {
            file = chooser.getSelectedFile();
            openFiles(Arrays.asList(file));
        }
    }

    private void copyCurrentData(ActionEvent e) {
        if (mainPanel != null) {
            final Component currentTab = mainPanel.getCurrentTab();
            if (currentTab != null) {
                if (currentTab instanceof SyntaxPanel) {
                    SyntaxPanel syntaxPanel = (SyntaxPanel) currentTab;
                    syntaxPanel.copySelectedTextIntoClipboard();
                }
            }
        }
    }

    private void selectAllCurrentData(ActionEvent e) {
        if (mainPanel != null) {
            final Component currentTab = mainPanel.getCurrentTab();
            if (currentTab != null) {
                if (currentTab instanceof SyntaxPanel) {
                    SyntaxPanel syntaxPanel = (SyntaxPanel) currentTab;
                    syntaxPanel.selectAllText();
                }
            }
        }
    }

    private void findAll(ActionEvent e) {
    }

    private void openPreferences(ActionEvent e) {
    }
}
