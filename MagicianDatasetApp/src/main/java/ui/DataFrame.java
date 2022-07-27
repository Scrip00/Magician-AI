/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import util.CamUtil;
import util.MicUtil;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.apache.commons.io.output.ByteArrayOutputStream;

/**
 *
 * @author Scrip0
 */
public class DataFrame extends javax.swing.JFrame {

    /**
     * Creates new form DataFrame
     */
    public DataFrame() {
        initComponents();
        setupUI();
        setupDB();
    }

    private void setupDB() {
        String[] ranks = new String[]{"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};
        String[] suits = new String[]{"clubs", "diamonds", "hearts", "spades"};
        for (String rank : ranks) {
            cbRanks.addItem(rank);
        }
        for (String suit : suits) {
            cbSuits.addItem(suit);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelCard = new javax.swing.JPanel();
        labelCard = new javax.swing.JLabel();
        panelFinal = new javax.swing.JPanel();
        btnRestart = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        panelStart = new javax.swing.JPanel();
        labelMic = new javax.swing.JLabel();
        labelCam = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        cbRanks = new javax.swing.JComboBox<>();
        cbSuits = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        panelCard.setLayout(new java.awt.GridBagLayout());

        labelCard.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        labelCard.setText("Your card");
        panelCard.add(labelCard, new java.awt.GridBagConstraints());

        panelFinal.setLayout(new java.awt.GridBagLayout());

        btnRestart.setText("Restart");
        btnRestart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRestartMouseClicked(evt);
            }
        });
        panelFinal.add(btnRestart, new java.awt.GridBagConstraints());

        btnExit.setText("Exit");
        btnExit.setToolTipText("");
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 0);
        panelFinal.add(btnExit, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(850, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        panelStart.setLayout(new java.awt.GridBagLayout());

        labelMic.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelMic.setText("Mic");
        labelMic.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        panelStart.add(labelMic, gridBagConstraints);

        labelCam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCam.setText("Cam");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        panelStart.add(labelCam, gridBagConstraints);

        btnStart.setText("Start");
        btnStart.setFocusPainted(false);
        btnStart.setFocusable(false);
        btnStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStartMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        panelStart.add(btnStart, gridBagConstraints);

        cbRanks.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 20);
        panelStart.add(cbRanks, gridBagConstraints);

        cbSuits.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        panelStart.add(cbSuits, gridBagConstraints);

        jLabel1.setText("Choose your card");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 20);
        panelStart.add(jLabel1, gridBagConstraints);

        getContentPane().add(panelStart);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStartMouseClicked
        startRecordingDataset(new String[]{cbRanks.getSelectedItem().toString(), cbSuits.getSelectedItem().toString()});
    }//GEN-LAST:event_btnStartMouseClicked

    private void btnRestartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestartMouseClicked
        panelFinal.setVisible(false);
        panelStart.setVisible(true);
        this.setContentPane(panelStart);
    }//GEN-LAST:event_btnRestartMouseClicked

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        exit();
    }//GEN-LAST:event_btnExitMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exit();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DataFrame().setVisible(true);
            }
        });
    }

    private void exit() {
        CamUtil c = new CamUtil();
        c.closeCam();
        this.dispose();
    }

    public DataFrame(JButton btnStart, JLabel labelCam, JLabel labelMic, JPanel panelStart) throws HeadlessException {
        this.btnStart = btnStart;
        this.labelCam = labelCam;
        this.labelMic = labelMic;
        this.panelStart = panelStart;
    }

    private void setupUI() {
        if (!isCamAvailable()) {
            labelCam.setText("Camera is available");
            labelCam.setForeground(Color.GREEN);
        } else {
            labelCam.setText("Camera is not available");
            labelCam.setForeground(Color.RED);
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (isCamAvailable()) {
                        labelCam.setText("Camera is available");
                        labelCam.setForeground(Color.GREEN);
                        Thread.currentThread().stop();
                    }
                }
            };

            scheduler.scheduleAtFixedRate(r, 0, 1000, TimeUnit.MILLISECONDS);
        }

        if (isMicAvailable()) {
            labelMic.setText("Microphone is available");
            labelMic.setForeground(Color.GREEN);
        } else {
            labelMic.setText("Microphone is not available");
            labelMic.setForeground(Color.RED);
        }
    }

    private boolean isCamAvailable() {
        CamUtil c = new CamUtil();
        return c.isCamAvailable();
    }

    private boolean isMicAvailable() {
        MicUtil m = new MicUtil();
        return m.isMicAvailable();
    }

    private void startRecordingDataset(String[] falseStrings) {
        String pathTrue = System.getProperty("user.dir") + "\\Data\\True";
        String pathFalse = System.getProperty("user.dir") + "\\Data\\False";
        if (!new File(pathTrue).exists()) {
            new File(pathTrue).mkdirs();
            new File(pathFalse).mkdirs();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_HH_mm_ss");

        new Thread(() -> {
            long delay = 2000L;

            SwingUtilities.invokeLater(() -> {
                panelStart.setVisible(false);
                panelCard.setVisible(true);
                this.setContentPane(panelCard);
            });

            CamUtil c = new CamUtil();
            MicUtil m = new MicUtil();

            String[] ranks_and_suits = new String[]{"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace", "clubs", "diamonds", "hearts", "spades"};

            for (int i = 0; i < ranks_and_suits.length; i++) {
                try {
                    labelCard.setText(ranks_and_suits[i]);
                    CountDownLatch syncLatch = new CountDownLatch(1);
                    AudioInputStream stream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\sounds\\" + "is it" + ".wav"));
                    Clip clip = AudioSystem.getClip();

                    clip.addLineListener(e -> {
                        if (e.getType() == LineEvent.Type.STOP) {
                            syncLatch.countDown();
                        }
                    });

                    clip.open(stream);
                    clip.start();
                    syncLatch.await();
                    
                    clip.stop();
                    clip.close();
                    
                    stream = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "\\src\\main\\java\\assets\\sounds\\" + ranks_and_suits[i] + ".wav"));

                    clip.open(stream);
                    clip.start();
                    syncLatch.await();

                    String path = System.getProperty("user.dir") + "\\Data";

                    if (ranks_and_suits[i].toLowerCase().equals(falseStrings[0].toLowerCase()) || ranks_and_suits[i].toLowerCase().equals(falseStrings[1].toLowerCase())) {
                        path += "\\False";
                    } else {
                        path += "\\True";
                    }

                    String name = formatter.format(new Date()) + "_" + ranks_and_suits[i];

                    path += "\\" + name;

                    File f = new File(path);
                    if (!f.exists()) {
                        f.mkdirs();
                    }

                    m.startRecording(path + "\\" + name + ".wav", delay);
                    c.startRecording(path, delay, 15);

                    Thread.sleep(delay);
                } catch (Exception ex) {
                    Logger.getLogger(DataFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            SwingUtilities.invokeLater(() -> {
                panelCard.setVisible(false);
                panelFinal.setVisible(true);
                this.setContentPane(panelFinal);
            });
        }).start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRestart;
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox<String> cbRanks;
    private javax.swing.JComboBox<String> cbSuits;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelCam;
    private javax.swing.JLabel labelCard;
    private javax.swing.JLabel labelMic;
    private javax.swing.JPanel panelCard;
    private javax.swing.JPanel panelFinal;
    private javax.swing.JPanel panelStart;
    // End of variables declaration//GEN-END:variables
}
