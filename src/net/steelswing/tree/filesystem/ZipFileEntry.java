/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree.filesystem;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * File: ZipFileEntry.java
 * Created on 16.03.2023, 18:50:16
 *
 * @author LWJGL2
 */
public class ZipFileEntry implements FileEntry {

    protected boolean directory;
    protected String name;
    protected String fullPath;
    protected byte[] bytes;

    public ZipFileEntry(boolean directory, String fullPath, String name, byte[] bytes) {
        this.directory = directory;
        this.fullPath = fullPath;
        this.name = name;
        this.bytes = bytes;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDirectory() {
        return directory;
    }

    @Override
    public InputStream getStream() throws Exception {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public long length() {
        return bytes.length;
    }

    @Override
    public String getFullName() {
        return fullPath;
    }

    @Override
    public String toString() {
        return "ZipFileEntry{" + "directory=" + directory + ", name=" + name + ", bytes=" + bytes + '}';
    }
}
