/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import net.steelswing.IconManager;

/**
 *
 * @author MrJavaCoder
 */
public class ExplorerTreeRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 1L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        if (node.getUserObject() != null && node.getUserObject() instanceof TreeObject) {
            TreeObject object = (TreeObject) node.getUserObject();
            if (object.getIcon() != null) {
                setIcon(object.getIcon());
                return this;
            }
        }
        setIcon(IconManager.FORMATS.PAGE_WHITE_16ICON);
        return this;
    }

}
