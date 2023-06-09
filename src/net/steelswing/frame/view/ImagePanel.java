/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.frame.view;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import net.steelswing.tree.filesystem.FileEntry;
import net.steelswing.util.SwingUtils;

/**
 *
 * @author LWJGL2
 */
public class ImagePanel extends javax.swing.JPanel {

    protected final FileEntry imageFile;

    public ImagePanel(FileEntry imageFile) {
        this.imageFile = imageFile;
        this.initComponents();

        try {

            CanvasPanel canvas = (CanvasPanel) canvasPanel;
            final BufferedImage readedImage = ImageIO.read(imageFile.getStream());
            canvas.setImage(readedImage);

            long fileSizeInBytes = imageFile.length();
            double fileSizeInKB = fileSizeInBytes / 1024f;
            double fileSizeInMB = fileSizeInKB / 1024f;
            double fileSizeInGB = fileSizeInMB / 1024f;

            String info = fileSizeInBytes + " Bytes";
            if (fileSizeInBytes > 1024) {
                info = String.format("%.2f", fileSizeInKB) + " KB";
            }
            if (fileSizeInKB > 1024) {
                info = String.format("%.2f", fileSizeInMB) + " MB";
            }
            if (fileSizeInMB > 1024) {
                info = String.format("%.2f", fileSizeInGB) + " GB";
            }

            infoLabel.setText(readedImage.getWidth() + "x" + readedImage.getHeight() + "     " + info);

            zoomSlider.addChangeListener((ChangeEvent event) -> {
                canvas.setScale(zoomSlider.getValue());
            });

            addMouseWheelListener((e) -> zoomSlider.setValue(zoomSlider.getValue() - e.getUnitsToScroll()));
        } catch (Throwable e) {
            SwingUtils.showErrorDialog("Failed to read image", e);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoLabel = new javax.swing.JLabel();
        canvasPanel = new net.steelswing.frame.view.CanvasPanel();
        zoomSlider = new javax.swing.JSlider();

        canvasPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.windowBorder));

        zoomSlider.setMaximum(500);
        zoomSlider.setMinimum(1);

        javax.swing.GroupLayout canvasPanelLayout = new javax.swing.GroupLayout(canvasPanel);
        canvasPanel.setLayout(canvasPanelLayout);
        canvasPanelLayout.setHorizontalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, canvasPanelLayout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addComponent(zoomSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        canvasPanelLayout.setVerticalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, canvasPanelLayout.createSequentialGroup()
                .addContainerGap(397, Short.MAX_VALUE)
                .addComponent(zoomSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(canvasPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel canvasPanel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JSlider zoomSlider;
    // End of variables declaration//GEN-END:variables

}
