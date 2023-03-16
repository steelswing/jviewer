/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.frame.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * File: CanvasPanel.java
 * Created on 16.03.2023, 15:20:14
 *
 * @author LWJGL2
 */
public class CanvasPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private float scale = 1;
    private BufferedImage image;

    public CanvasPanel() {
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        {
            if (image == null) {
                return;
            }

            int finalW = (int) (image.getWidth() * scale);
            int finalH = (int) (image.getHeight() * scale);

            if (finalW <= 0) {
                finalW = 1;
            }
            if (finalH <= 0) {
                finalH = 1;
            }

            final Image scaled = image;

            g.drawImage(scaled, getWidth() / 2 - finalW / 2, getHeight() / 2 - finalH/ 2, finalW, finalH, null);
        }
        super.paintComponents(g);
    }


    public float getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale / 50f;
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
