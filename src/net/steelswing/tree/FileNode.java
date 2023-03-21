/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree;

import java.util.ArrayList;
import java.util.List;
import net.steelswing.tree.filesystem.FileEntry;

/**
 * File: FileNode.java
 * Created on 26.08.2022, 7:23:54
 *
 * @author LWJGL2
 */
public class FileNode {

    protected List<FileNode> children = new ArrayList<>();

    protected String fileName;
    protected FileEntry file;

    protected boolean isDir;
    protected boolean isRoot;
    protected boolean readable = true;

    public FileNode(FileEntry file, boolean isRoot) {
        this.file = file;
        this.fileName = file.getName();
        this.isDir = file.isDirectory();
        this.isRoot = isRoot;
    }

    public FileNode(FileEntry file) {
        this(file, false);
    }



    public List<FileNode> getChildren() {
        return children;
    }

    public void setChildren(List<FileNode> children) {
        this.children = children;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isZipArchive() {
        String extension = getExtension();
        return "zip".equals(extension) || "jar".equals(extension) || "war".equals(extension);
    }

    public String getExtension() {
        try {
            String[] split = fileName.split("\\.");
            if (split.length > 1) {
                return split[split.length - 1].toLowerCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setIsDir(boolean isDir) {
        this.isDir = isDir;
    }

    public FileEntry getFile() {
        return file;
    }

    public void setFile(FileEntry file) {
        this.file = file;
    }

    public void addChild(FileNode child) {
        this.children.add(child);
    }

    public boolean isRoot() {
        return isRoot;
    }

    public FileNode getChild(String name) {
        for (FileNode fileNode : children) {
            if (fileNode.fileName.equals(name)) {
                return fileNode;
            }
        }
        return null;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    @Override
    public String toString() {
        return "FileNode{" + "children=" + children.size() + ", fileName=" + fileName + ", file=" + file + ", isDir=" + isDir + ", isRoot=" + isRoot + ", readable=" + readable + '}';
    }

}
