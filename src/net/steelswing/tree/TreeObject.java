/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree;

import javax.swing.ImageIcon;

/**
 *
 * @author MrJavaCoder
 * 
 * @param <K> key
 * @param <V> value
 */
public class TreeObject<K, V> {

    protected K name;
    protected final V value;
    
    protected ImageIcon icon;

    public TreeObject(K name, V value, ImageIcon icon) {
        this.name = name;
        this.value = value;
        this.icon = icon;
    }

    public TreeObject(K name, V value) {
        this(name, value, null);
    }
    
    @Override
    public String toString() {
        return (String) getName();
    }

    public K getName() {
        return name;
    }

    public V getValue() {
        return value;
    }

    public void setName(K name) {
        this.name = name;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }
   
}
