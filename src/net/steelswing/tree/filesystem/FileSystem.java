/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree.filesystem;

import javax.swing.tree.TreePath;
import net.steelswing.tree.ExplorerTreeNode;
import net.steelswing.tree.TreeHandler;

/**
 * File: FileSystem.java
 * Created on 16.03.2023, 17:55:09
 *
 * @author LWJGL2
 */
public interface FileSystem {

    public void createTree(TreeHandler treeHandler) throws Exception;
    
    
    public default void expandTree(ExplorerTreeNode root, TreeHandler treeHandler) {
        if (root.getChildCount() > 0) {
            ExplorerTreeNode childAt = (ExplorerTreeNode) root.getChildAt(0);
            treeHandler.getTree().expandPath(new TreePath(childAt.getPath()));
        }
    }
}
