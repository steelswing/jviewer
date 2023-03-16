/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.frame;

import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.TransferHandler;
import net.steelswing.util.SwingUtils;

/**
 * File: FilesTransferHandler.java
 * Created on 16.03.2023, 18:07:25
 *
 * @author LWJGL2
 */
public class FilesTransferHandler extends TransferHandler {

    protected final Consumer<List<File>> consumer;

    public FilesTransferHandler(Consumer<List<File>> consumer) {
        this.consumer = consumer;
    }



    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        return info.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport info) {
        if (info.isDrop() && info.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            try {
                List<File> files = (List<File>) info.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                consumer.accept(files);
                return true;
            } catch (Exception e) {
                SwingUtils.showErrorDialog("Failed to process drag and drop", e);
            }
        }
        return false;
    }

}
