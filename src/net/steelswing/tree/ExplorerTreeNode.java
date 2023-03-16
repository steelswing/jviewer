/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import net.steelswing.tree.filesystem.FileEntry;

/**
 * File: ExplorerTreeNode.java
 * Created on 26.08.2022, 6:48:53
 *
 * @author LWJGL2
 */
public class ExplorerTreeNode extends DefaultMutableTreeNode {

    private static final long serialVersionUID = 1L;
    private FileNode fileNode;

    public ExplorerTreeNode() {
    }

    public ExplorerTreeNode(TreeObject<String, FileEntry> o) {
        super(o);
    }

    public ExplorerTreeNode(TreeObject<String, FileEntry> o, boolean bln) {
        super(o, bln);
    }

    public FileNode getFileNode() {
        return fileNode;
    }

    public TreeObject<String, FileEntry> getObject() {
        return (TreeObject<String, FileEntry>) userObject;
    }
    
    public ExplorerTreeNode setFileNode(FileNode fileNode) {
        this.fileNode = fileNode;
        return this;
    }

 
}
