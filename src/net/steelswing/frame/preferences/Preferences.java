/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.frame.preferences;

import net.steelswing.frame.MainFrame;
import net.steelswing.tree.TreeHandler;

/**
 * File: Preferences.java
 * Created on 18.03.2023, 18:32:47
 *
 * @author LWJGL2
 */
public class Preferences {

    protected final MainFrame mainFrame;
    protected boolean showSubClasses = false;
    protected DecompilerType decompilerType = DecompilerType.PROCYON;

    public Preferences(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public boolean isShowSubClasses() {
        return showSubClasses;
    }

    public void setShowSubClasses(boolean showSubClasses) {
        this.showSubClasses = showSubClasses;
        if (mainFrame.getMainPanel() != null) {
            TreeHandler treeHandler = mainFrame.getMainPanel().getTreeHandler();
            if (!showSubClasses) {
                treeHandler.removeSubclasses();
            } else {
                treeHandler.createTree();
            }
        }
    }

    public DecompilerType getDecompilerType() {
        return decompilerType;
    }

    public void setDecompilerType(DecompilerType decompilerType) {
        this.decompilerType = decompilerType;
    }

    public static enum DecompilerType {
        FERNFLOWER,
        CFR,
        PROCYON,
        BYTECODE,
        BYTECODE_AST;
    }
}
