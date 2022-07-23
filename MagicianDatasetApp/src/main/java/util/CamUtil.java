/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
import ui.DataFrame;

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
        if (cam == null || !cam.isOpen()) {
            cam = Webcam.getDefault();
            cam.setViewSize(new Dimension(640, 480)); // 320x240 640x480 176x144
            cam.open();
        }

        new Thread(() -> {
            int c = 1;
            int frames = (int) (length / (1000 / fps));
            try {
                while (c <= frames) {
                    long timeBefore = System.currentTimeMillis();
                    Image image = cam.getImage();

                    ImageIO.write((RenderedImage) image, "png", new File(path + "\\image_" + c + ".png"));

                    long elapsed = System.currentTimeMillis() - timeBefore;
                    long delay = 1000 / fps - elapsed;
                    if (delay < 0) {
                        delay = 0;
                    }
                    Thread.sleep(delay);
                    c++;
                }
            } catch (Exception ex) {
                Logger.getLogger(DataFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }).start();
    }

    public void closeCam() {
        cam.close();
    }
}
