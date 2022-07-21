/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrip0.magiciandatasetapp.util;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Scrip0
 */
public class CamUtil implements WebcamDiscoveryListener {

    private Thread camThread;

    private int c;

    private Webcam cam;

    private boolean isCamAvailable = true;

    public CamUtil() {
        for (Webcam webcam : Webcam.getWebcams()) {
            System.out.println("Webcam detected: " + webcam.getName());
        }
        Webcam.addDiscoveryListener(this);
    }

    public boolean isCamAvailable() {
        return isCamAvailable;
    }

    @Override
    public void webcamFound(WebcamDiscoveryEvent wde) {
        isCamAvailable = true;
    }

    @Override
    public void webcamGone(WebcamDiscoveryEvent wde) {
        isCamAvailable = true;
    }

    public void startRecording(String path, Long length, int fps) {
        cam = Webcam.getDefault();
        cam.setViewSize(new Dimension(176, 144)); // 320x240
        cam.open();

        Thread thread = new Thread(() -> {
            c = 1;
            while (true) {
                try {
                    Image image = cam.getImage();
                    new Thread(() -> {
                        try {
                            ImageIO.write((RenderedImage) image, "png", new File(path + "\\image_" + c + ".png"));
                        } catch (IOException ex) {
                            Logger.getLogger(CamUtil.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }).start();

                    Thread.sleep(1000 / fps);
                    c++;
                } catch (Exception ex) {
                    Logger.getLogger(CamUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        camThread = thread;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                camThread.stop();
                camThread.suspend();
                camThread.interrupt();
                
                try {
                    camThread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(CamUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(camThread.isAlive());
                cam.close();
            }
        };

        thread.start();
        scheduler.schedule(r, length, TimeUnit.MILLISECONDS);
    }
}
