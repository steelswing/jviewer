/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.tree.decompiler.fernflower;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.steelswing.tree.filesystem.FileSystem;
import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;
import org.jetbrains.java.decompiler.main.extern.IFernflowerPreferences;

/**
 * File: FernFlowerDecompiler.java
 * Created on 18.03.2023, 20:31:08
 *
 * @author LWJGL2
 */
public class FernFlowerDecompiler {

    private static final FernFlowerLogger LOGGER = new FernFlowerLogger();
    private static final DummyCollector DUMMY_COLLECTOR = new DummyCollector();
    private FernFlowerAccessor decompiler;
    private Map<String, Object> options;
    private FileSystem fileSystem;

    public FernFlowerDecompiler(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
        this.options = generateDefaultOptions();
    }

    protected Map<String, Object> generateDefaultOptions() {
        Map<String, Object> map = new HashMap<>(IFernflowerPreferences.getDefaults());
        map.put("ind", "    ");
        // FernFlower doesn't have options against intentional marking of ACC_SYNTHETIC by obfuscators :/
        // This will only show ACC_BRIDGE but not ACC_SYNTHETIC
        map.put("rbr", "0"); // hide bridge methods
        map.put("rsy", "0"); // hide synthetic class members
//        if (!config.stripDebug) {
//            map.put("dgs", "1"); // decompile generics
//        }
        return map;
    }

    public String decompile(String name) {
        // Dump class content
        return decompiler.decompile(name);
    }

    /**
     * Initialize FernFlower with the given workspace.
     *
     * @param workspace
     * Workspace to pull classes from.
     */
    public void setup() {
        IBytecodeProvider provider = (externalPath, internalPath) -> {
            if (internalPath != null) {
                String className = internalPath.substring(0, internalPath.indexOf(".class"));
                System.out.println("GET CLASS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + className);
//                byte[] code = workspace.getRawClass(className);
//                return code;
            }
            throw new IllegalStateException("Provider should only receive internal names." +
                    "Got external name: " + externalPath);
        };
        decompiler = new FernFlowerAccessor(provider, DUMMY_COLLECTOR, options, LOGGER);
        try {
            decompiler.addFileSystem(fileSystem);
            decompiler.analyze();
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load inputs for FernFlower!", ex);
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalStateException("FernFlower internal error", ex);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
