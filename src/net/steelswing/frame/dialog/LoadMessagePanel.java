/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing.frame.dialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author LWJGL2
 */
public class LoadMessagePanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private final JDialog dialog;

    public LoadMessagePanel(JDialog dialog, String text) {
        this.dialog = dialog;
        this.initComponents();

        this.infoLabel.setText(text);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        infoLabel.setText("jLabel3");

        progressBar.setMaximum(0);
        progressBar.setIndeterminate(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    public static JDialog showInfo(JFrame frame, String title, String message) {
        JDialog dialog = new JDialog(frame, title);
        new Thread(() -> {
            LoadMessagePanel panel = new LoadMessagePanel(dialog, message);
            dialog.add(panel);
            dialog.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        dialog.dispose();
                    }
                }
            });
            dialog.setModal(true);
            dialog.setSize(400, 100);
            dialog.setLocationRelativeTo(frame);
            dialog.setDefaultCloseOperation(0);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

            });
            dialog.revalidate();
            dialog.repaint();
            dialog.setVisible(true);
        }).start();
        return dialog;
    }

    public static LoadingMenuResult showInfo2(JFrame frame, String title, String message) {
        JDialog dialog = new JDialog(frame, title);
        LoadMessagePanel panel = new LoadMessagePanel(dialog, message);
        new Thread(() -> {
            dialog.add(panel);
            dialog.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        dialog.dispose();
                    }
                }
            });

            dialog.setModal(true);
            dialog.setSize(400, 100);
            dialog.setLocationRelativeTo(frame);
            dialog.setDefaultCloseOperation(0);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

            });
            try {
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                dialog.dispose();
            }
        }).start();
        return new LoadingMenuResult(dialog, panel);
    }

    public static class LoadingMenuResult {

        public JDialog dialog;
        public LoadMessagePanel panel;

        public LoadingMenuResult(JDialog dialog, LoadMessagePanel panel) {
            this.dialog = dialog;
            this.panel = panel;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel infoLabel;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }

}
