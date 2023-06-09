/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import net.steelswing.IconManager;
import net.steelswing.frame.MainPanel;
import net.steelswing.frame.dialog.LoadMessagePanel;
import net.steelswing.tree.decompiler.fernflower.FernFlowerDecompiler;
import net.steelswing.tree.filesystem.FileEntry;
import net.steelswing.tree.filesystem.FileSystem;
import net.steelswing.util.JTreeUtil;
import net.steelswing.util.SwingUtils;

/**
 * File: TreeHandler.java
 * Created on 26.08.2022, 6:44:25
 *
 * @author LWJGL2
 */
public class TreeHandler {

    private final MainPanel panel;
    private final JTree tree;
    private FileSystem fileSystem;

    private ExplorerTreeNode root;

    private FernFlowerDecompiler decompiler;

    private List<File> files = new ArrayList<>();
    private final Comparator<FileNode> fileComparator = (a, b) -> {
        int dir = Boolean.compare(b.isDir(), a.isDir());
        if (dir != 0) {
            return dir;
        }
        return a.getFileName().compareTo(b.getFileName());
    };

    public TreeHandler(MainPanel panel, JTree tree, FileSystem fileSystem) {
        this.panel = panel;
        this.tree = tree;
        this.fileSystem = fileSystem;
        tree.setCellRenderer(new ExplorerTreeRenderer());
        tree.setRootVisible(false);
        tree.setScrollsOnExpand(true);

        createTree();

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                try {
                    TreePath selPath = tree.getPathForLocation(event.getX(), event.getY());
                    if (selPath != null) {
                        onSelectPath(event, tree.getSelectionPaths());
                    } else {
                        if (SwingUtilities.isRightMouseButton(event)) {
                            JPopupMenu menu = new JPopupMenu();
                            JMenuItem expandAll = new JMenuItem("Expand All", IconManager.TOOL.TREE_EXPAND_16ICON);
                            JMenuItem collapseAll = new JMenuItem("Collapse All", IconManager.TOOL.TREE_COLLAPSE_16ICON);
                            JMenuItem refresh = new JMenuItem("Refresh", IconManager.TOOL.REFRESH_16ICON);
                            refresh.addActionListener((e) -> createTree());
                            expandAll.addActionListener((e) -> JTreeUtil.setTreeExpandedState(tree, true));
                            collapseAll.addActionListener((e) -> JTreeUtil.setTreeExpandedState(tree, false));
                            menu.add(refresh);
                            menu.add(expandAll);
                            menu.add(collapseAll);
                            SwingUtils.showPopupMenu(menu, tree, event.getX(), event.getY());
                        }
                    }
                } catch (Throwable e) {
                    SwingUtils.showErrorDialog("Tree handler error", e);
                }
            }

        });
    }

    public synchronized void createTree() {
        LoadMessagePanel.LoadingMenuResult loadDialog = LoadMessagePanel.showInfo2(panel.getMainFrame(), "Creating tree", "Please wait");
        new Thread(() -> {
            try {
                Thread.sleep(500);
                fileSystem.createTree(this);
            } catch (Throwable e) {
                SwingUtils.showErrorDialog("Failed to create tree using " + fileSystem.getClass().getName(), e);
            }
            if (!panel.getMainFrame().getPreferences().isShowSubClasses()) {
                removeSubclasses();
            }

            loadDialog.panel.getInfoLabel().setText("Preparing FernFlower...");
            this.decompiler = new FernFlowerDecompiler(fileSystem);
            SwingUtilities.invokeLater(() -> {
                try {
                    this.decompiler.setup();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                while (loadDialog.dialog.isVisible()) {
                    loadDialog.dialog.dispose();
                }
            });

        }).start();
    }

    public synchronized void removeSubclasses() {
        List<ExplorerTreeNode> nodes = JTreeUtil.<ExplorerTreeNode>getTreeNodes(tree);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        for (ExplorerTreeNode node : nodes) {
            if (node.getFileNode() != null) {
                final String fileName = node.getFileNode().getFileName();
                if (fileName.endsWith(".class") && fileName.contains("$")) {
                    model.removeNodeFromParent(node);
                }
            }
        }
    }

    protected synchronized void onSelectPath(MouseEvent event, TreePath... paths) {
        if (paths == null || paths.length < 1) {
            return;
        }

        ExplorerTreeNode node = getNode(paths[0]);
        if (node == null) {
            return;
        }
        FileNode fileNode = node.getFileNode();
        if (fileNode == null) {
            return;
        }
        if (fileNode.isDir() || !fileNode.isReadable()) {
            return;
        }
        TreeObject object = node.getObject();
        if (object == null) {
            return;
        }

        ImageIcon icon = object.getIcon();
        try {
            System.out.println("Reading: " + fileNode.getFileName());

            try (InputStream stream = fileNode.file.getStream()) {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                final byte[] buffer = new byte[8096];
                int bytesRead;
                // побайтово считываем и записываем файлик
                while ((bytesRead = stream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }

                System.out.println("Done!");
                String toString = output.toString("UTF-8");

                if (fileNode.getExtension().equals("class")) {
                    toString = "/* Failed to decompile */";
                    try {
                        if (decompiler != null) {
                            toString = decompiler.decompile(fileNode.getFile().getFullName().replace(".class", ""));
                        }
                    } catch (Throwable e) {
                        SwingUtils.showErrorDialog("Failed to decoimpile", e);
                    }
                }

                panel.openFile(fileNode, icon, toString);
            }
        } catch (Exception e) {
            System.out.println("Failed to open file!");
            e.printStackTrace();
        }
//        System.out.println(object + " " + fileNode.getFile());
    }

    public synchronized void createTree(FileNode node, ExplorerTreeNode treeNode) {
        if (node == null) {
            return;
        }
        ExplorerTreeNode newNode = createNode(node, new TreeObject<>(node.getFileName(), node.getFile(), IconManager.FORMATS.getByExtention(node)));
        addNode(treeNode, newNode);

        node.children.sort(fileComparator);
        for (FileNode fileNode : node.children) {
            createTree(fileNode, newNode);
        }
    }

    public synchronized ExplorerTreeNode getRoot() {
        return root;
    }

    public synchronized ExplorerTreeNode createNode(FileNode node, TreeObject<String, FileEntry> object) {
        return new ExplorerTreeNode(object).setFileNode(node);
    }

    public synchronized ExplorerTreeNode getNode(TreePath path) {
        ExplorerTreeNode node = (ExplorerTreeNode) path.getLastPathComponent();
        if (node == null) {
            return null;
        }
        if (!(node.getUserObject() instanceof TreeObject)) {
            return null;
        }
        return node;
    }

    public synchronized void addNode(ExplorerTreeNode root, ExplorerTreeNode node) {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.insertNodeInto(node, root, root.getChildCount());
    }

    public synchronized void reloadNode(TreeNode node) {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.reload(node);
    }


    public MainPanel getPanel() {
        return panel;
    }

    public synchronized JTree getTree() {
        return tree;
    }

    public List<File> getFiles() {
        return files;
    }

    public Comparator<FileNode> getFileComparator() {
        return fileComparator;
    }

    public synchronized void setRoot(ExplorerTreeNode root) {
        this.root = root;
    }
}
