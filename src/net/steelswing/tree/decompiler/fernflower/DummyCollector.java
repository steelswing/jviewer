
package net.steelswing.tree.decompiler.fernflower;

import java.util.jar.Manifest;
import org.jetbrains.java.decompiler.main.extern.IResultSaver;

/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

/**
 * File: DummyCollector.java
 * Created on 18.03.2023, 20:24:15
 *
 * @author LWJGL2
 */
public class DummyCollector implements IResultSaver {

    @Override
    public void saveFolder(String s) {
    }

    @Override
    public void copyFile(String s, String s1, String s2) {
    }

    @Override
    public void saveClassFile(String s, String s1, String s2, String s3, int[] ints) {
    }

    @Override
    public void createArchive(String s, String s1, Manifest manifest) {
    }

    @Override
    public void saveDirEntry(String s, String s1, String s2) {
    }

    @Override
    public void copyEntry(String s, String s1, String s2, String s3) {
    }

    @Override
    public void closeArchive(String s, String s1) {
    }
}
