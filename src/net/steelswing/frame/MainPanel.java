/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.frame;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.steelswing.IconManager;
import net.steelswing.frame.view.ImagePanel;
import net.steelswing.frame.view.SyntaxPanel;
import net.steelswing.tree.FileNode;
import net.steelswing.tree.TreeHandler;
import net.steelswing.tree.filesystem.FileSystem;

/**
 *
 * @author LWJGL2
 */
public class MainPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    protected final MainFrame mainFrame;
    protected TreeHandler treeHandler;

    private Map<FileNode, Integer> pages = new ConcurrentHashMap<>();

    public MainPanel(MainFrame mainFrame, FileSystem fileSystem) {
        this.mainFrame = mainFrame;
        this.initComponents();
        this.tabbedPanel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.treeHandler = new TreeHandler(this, projectTree, fileSystem);

        this.setTransferHandler(new FilesTransferHandler((files) -> mainFrame.openFiles(files)));
    }

    public void openFile(FileNode fileNode, ImageIcon icon, String data) {
        Integer pageIndex = pages.get(fileNode);
        if (pageIndex != null) {
            tabbedPanel.setSelectedIndex(pageIndex);
            return;
        }

        JPanel panel = null;

        if (IconManager.FORMATS.isImage(fileNode.getExtension())) {
            panel = new ImagePanel(fileNode.getFile());
        }

        if (panel == null) {
            SyntaxPanel syntaxPanel = new SyntaxPanel(fileNode);
            syntaxPanel.setContent(data);
            panel = syntaxPanel;
        }

        final String title = fileNode.getFileName();
        tabbedPanel.addTab(title, icon, panel);
        {
            ClosableTab ct = new ClosableTab(fileNode, tabbedPanel, title, icon, () -> closeTab(fileNode, title), () -> closeAllTabs(), () -> closeOtherPages(title));
            tabbedPanel.setTabComponentAt(tabbedPanel.indexOfTab(title), ct);
        }

        int index = tabbedPanel.getTabCount() - 1;
        tabbedPanel.setSelectedIndex(index);
        pages.put(fileNode, index);
    }
    
    public Component getCurrentTab() {
        return tabbedPanel.getSelectedComponent();
    }

    public void closeAllTabs() {
        pages.clear();
        tabbedPanel.removeAll();
    }

    public void closeTab(FileNode fileNode, final String title) {
        pages.remove(fileNode);
        tabbedPanel.remove(tabbedPanel.indexOfTab(title));
    }

    public void closeOtherPages(final String title) {
        List<Integer> tabListToRemove = new ArrayList<>();
        for (int i = 0; i < tabbedPanel.getTabCount(); i++) {
            final int indexOfTab = tabbedPanel.indexOfTab(title);
            if (i == indexOfTab) {
                continue;
            }
            tabListToRemove.add(i);
        }
        int count = 0;
        for (Integer integer : tabListToRemove) {
            final int tabIndex = integer - count;
            tabbedPanel.removeTabAt(tabIndex);
            for (Map.Entry<FileNode, Integer> entry : pages.entrySet()) {
                Integer value = entry.getValue();
                if (value == tabIndex) {
                    pages.remove(entry.getKey());
                }
            }

            count++;
        }
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitMain = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        projectTree = new javax.swing.JTree();
        tabbedPanel = new javax.swing.JTabbedPane();

        splitMain.setDividerLocation(200);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        projectTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        projectTree.setToolTipText("");
        jScrollPane1.setViewportView(projectTree);

        splitMain.setLeftComponent(jScrollPane1);

        tabbedPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));
        splitMain.setRightComponent(tabbedPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitMain, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitMain, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree projectTree;
    private javax.swing.JSplitPane splitMain;
    private javax.swing.JTabbedPane tabbedPanel;
    // End of variables declaration//GEN-END:variables

}
