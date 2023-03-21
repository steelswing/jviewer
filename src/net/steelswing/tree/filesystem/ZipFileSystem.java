/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree.filesystem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.tree.DefaultTreeModel;
import net.steelswing.IconManager;
import net.steelswing.tree.ExplorerTreeNode;
import net.steelswing.tree.FileNode;
import net.steelswing.tree.TreeHandler;
import net.steelswing.tree.TreeObject;
import net.steelswing.util.Pair;
import net.steelswing.util.SwingUtils;

/**
 * File: ZipFileSystem.java
 * Created on 16.03.2023, 18:18:19
 *
 * @author LWJGL2
 */
public class ZipFileSystem implements FileSystem {

    protected InputStream zipFile;
    protected ZipInputStream zip;
    protected String fileName;

    protected Map<String, byte[]> classes = new HashMap<>();

    public ZipFileSystem(File zipFile) throws FileNotFoundException {
        this(new FileInputStream(zipFile), zipFile.getName());
    }

    public ZipFileSystem(InputStream zipFile, String fileName) {
        this.zipFile = zipFile;
        this.fileName = fileName;

    }

    @Override
    public void createTree(TreeHandler treeHandler) throws Exception {

        ExplorerTreeNode rootTreeNode = treeHandler.createNode(null, new TreeObject<>("Project", (FileEntry) null, IconManager.BRICKS.BRICKS_16ICON));
        treeHandler.getTree().setModel(new DefaultTreeModel(rootTreeNode));

        treeHandler.createTree(createRoot(null), rootTreeNode);
        expandTree(rootTreeNode, treeHandler);
    }

    public FileNode createRoot(String rootNameOrNull) throws FileNotFoundException, IOException {
        if (zip == null) {
            zip = new ZipInputStream(zipFile);
        }
        FileNode rootFileNode = new FileNode(new ZipFileEntry(true, rootNameOrNull == null ? fileName : rootNameOrNull, rootNameOrNull == null ? fileName : rootNameOrNull, new byte[0]), true);
        byte[] buffer = new byte[4096 * 4];

        Map<String, Pair<ZipEntry, ByteArrayOutputStream>> entries = new HashMap<>();
        for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            try {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                for (int len = 0; (len = zip.read(buffer)) > 0;) {
                    stream.write(buffer, 0, len);
                }
                entries.put(entry.getName(), new Pair<>(entry, stream));
                if (entry.getName().endsWith(".class")) {
                    classes.put(entry.getName(), stream.toByteArray());
                }
            } catch (Throwable e) {
                SwingUtils.showErrorDialog("Failed to create tree from zip/jar", e);
            }
        }

        {
            for (Map.Entry<String, Pair<ZipEntry, ByteArrayOutputStream>> mapEntry : entries.entrySet()) {
                String key = mapEntry.getKey();
                Pair<ZipEntry, ByteArrayOutputStream> value = mapEntry.getValue();

                ZipEntry entry = value.getKey();
                ByteArrayOutputStream stream = value.getValue();

                if (entry.isDirectory()) {
                    String[] split = key.split("/");
                    FileNode root = rootFileNode;
                    for (String string : split) {

                        FileNode child = root.getChild(string);
                        if (child == null) {
                            root.addChild(child = new FileNode(new ZipFileEntry(entry.isDirectory(), key, string, stream.toByteArray())));
                        }
                        root = child;
                    }
                }
            }

            for (Map.Entry<String, Pair<ZipEntry, ByteArrayOutputStream>> mapEntry : entries.entrySet()) {
                String key = mapEntry.getKey();
                Pair<ZipEntry, ByteArrayOutputStream> value = mapEntry.getValue();

                ZipEntry entry = value.getKey();
                ByteArrayOutputStream stream = value.getValue();

                if (!entry.isDirectory()) {
                    String[] split = key.split("/");
                    FileNode root = rootFileNode;
                    for (String string : split) {

                        FileNode child = root.getChild(string);
                        if (child == null) {
                            final byte[] toByteArray = stream.toByteArray();
                            child = new FileNode(new ZipFileEntry(entry.isDirectory(), key, string, toByteArray));
                            if (child.isZipArchive()) {
                                child.setReadable(false);
                                ZipFileSystem zipFileSystem = new ZipFileSystem(new ByteArrayInputStream(toByteArray), entry.getName());
                                try {
                                    child.addChild(zipFileSystem.createRoot("root"));
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }

                            root.addChild(child);
                        }
                        root = child;
                    }
                }
            }
        }

        zip.close();
        return rootFileNode;
    }

    @Override
    public Set<Map.Entry<String, byte[]>> getClasses() {
        return classes.entrySet();
    }



}
