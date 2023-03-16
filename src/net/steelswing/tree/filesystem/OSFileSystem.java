/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree.filesystem;

import java.io.File;
import java.io.IOException;
import javax.swing.tree.DefaultTreeModel;
import net.steelswing.IconManager;
import net.steelswing.tree.ExplorerTreeNode;
import net.steelswing.tree.FileNode;
import net.steelswing.tree.TreeHandler;
import net.steelswing.tree.TreeObject;

/**
 * File: OSFileSystem.java
 * Created on 16.03.2023, 17:56:21
 *
 * @author LWJGL2
 */
public class OSFileSystem implements FileSystem {

    private File folder;

    public OSFileSystem(File folder) {
        this.folder = folder;
    }

    @Override
    public void createTree(TreeHandler treeHandler) {
        ExplorerTreeNode root = treeHandler.createNode(null, new TreeObject("Project", null, IconManager.BRICKS.BRICKS_16ICON));

        treeHandler.getTree().setModel(new DefaultTreeModel(root));

        FileNode rootFileNode = new FileNode(OSFileEntry.of(folder), true);
        processFilesFromFolder(rootFileNode, folder);
        treeHandler.createTree(rootFileNode, root);
        expandTree(root, treeHandler);
    }


    protected void processFilesFromFolder(FileNode rootFileNode, File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            FileNode child = new FileNode(OSFileEntry.of(entry));
            if (entry.isDirectory()) {
                child.setIsDir(true);
                processFilesFromFolder(child, entry);
            }
            if (child.isZipArchive()) {
                child.setReadable(false);
                try {
                    ZipFileSystem zipFileSystem = new ZipFileSystem(entry);
                    child.addChild(zipFileSystem.createRoot("root"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            rootFileNode.addChild(child);

        }
    }

}
