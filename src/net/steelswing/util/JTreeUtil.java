/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * File: JTreeUtil.java
 * Created on 24.08.2022, 12:14:23
 *
 * @author LWJGL2
 */
public class JTreeUtil {

    public static void setTreeExpandedState(JTree tree, boolean expanded) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getModel().getRoot();
        setNodeExpandedState(tree, node, expanded);
    }

    @SuppressWarnings("unchecked")
    public static void setNodeExpandedState(JTree tree, DefaultMutableTreeNode node, boolean expanded) {
        ArrayList<?> raw = Collections.list(node.children());
        ArrayList<DefaultMutableTreeNode> list = (ArrayList<DefaultMutableTreeNode>) raw;
        for (DefaultMutableTreeNode treeNode : list) {
            setNodeExpandedState(tree, treeNode, expanded);
        }
        if (!expanded && node.isRoot()) {
            return;
        }
        TreePath path = new TreePath(node.getPath());
        if (expanded) {
            tree.expandPath(path);
        } else {
            tree.collapsePath(path);
        }
    }

    public static List<DefaultMutableTreeNode> getTreeNodes(JTree tree) {
        List<DefaultMutableTreeNode> result = new ArrayList<>();

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        Enumeration e = root.preorderEnumeration();
        while (e.hasMoreElements()) {
            try {
                result.add((DefaultMutableTreeNode) e.nextElement());
            } catch (Exception ex) {
            }
        }
        return result;
    }

    public static TreePath getPath(TreeNode treeNode) {
        List<Object> nodes = new ArrayList<>();
        if (treeNode != null) {
            nodes.add(treeNode);
            treeNode = treeNode.getParent();
            while (treeNode != null) {
                nodes.add(0, treeNode);
                treeNode = treeNode.getParent();
            }
        }

        return nodes.isEmpty() ? null : new TreePath(nodes.toArray());
    }
}
