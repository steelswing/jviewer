/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * File: OSFileEntry.java
 Created on 16.03.2023, 18:26:17
 *
 * @author LWJGL2
 */
public class OSFileEntry implements FileEntry {

    protected File file;

    public OSFileEntry(File file) {
        this.file = file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }

    @Override
    public InputStream getStream() throws Exception {
        return new FileInputStream(file);
    }

    @Override
    public long length() {
        return file.length();
    }

    public File getFile() {
        return file;
    }

    @Override
    public String getFullName() {
        return file.getAbsolutePath();
    }

    public static OSFileEntry of(File file) {
        return new OSFileEntry(file);
    }

    @Override
    public String toString() {
        return "OSFileEntry{" + "file=" + file + '}';
    }
}
