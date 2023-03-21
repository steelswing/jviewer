/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree.decompiler.fernflower;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import net.steelswing.tree.filesystem.FileSystem;
import org.jetbrains.java.decompiler.main.extern.IResultSaver;
import org.jetbrains.java.decompiler.struct.IDecompiledData;
import org.jetbrains.java.decompiler.struct.StructContext;
import org.jetbrains.java.decompiler.struct.lazy.LazyLoader;

/**
 * File: StructContextDecorator.java
 * Created on 18.03.2023, 20:27:58
 *
 * @author LWJGL2
 */
public class StructContextDecorator extends StructContext {

    /**
     * Constructs a StructContext.
     *
     * @param saver
     * Result saver <i>(Unused/noop)</i>
     * @param data
     * Data instance, should be an instance of
     * {@link me.coley.recaf.decompile.fernflower.FernFlowerAccessor}.
     * @param loader
     * LazyLoader to hold links to class resources.
     */
    public StructContextDecorator(IResultSaver saver, IDecompiledData data, LazyLoader loader) {
        super(saver, data, loader);
    }


    public void addFileSystem(FileSystem fileSystem) throws IOException {
        Set<Map.Entry<String, byte[]>> files = fileSystem.getClasses();

        for (Map.Entry<String, byte[]> file : files) {
            String name = file.getKey();
            if (name.endsWith(".class")) {

                byte[] code = file.getValue();

                System.out.println("add " + name + " " + code.length);
                String simpleName = name.substring(name.lastIndexOf('/') + 1);
                addData(name, simpleName, code, true);
            }
        }
    }
}
