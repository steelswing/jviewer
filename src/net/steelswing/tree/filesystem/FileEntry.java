/*
Ну вы же понимаете, что код здесь только мой?
Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree.filesystem;

import java.io.InputStream;

/**
 * File: FileEntry.java
 * Created on 16.03.2023, 18:21:46
 *
 * @author LWJGL2
 */
public interface FileEntry {

    public String getName();
    
    public boolean isDirectory();

    public InputStream getStream() throws Exception;

    public long length();

    public String getFullName();
}
