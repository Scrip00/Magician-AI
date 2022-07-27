/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage;
import org.bytedeco.opencv.opencv_core.IplImage;

/**
 *
 * @author Scrip0
 */
public class CamUtil implements WebcamDiscoveryListener {
    
    FrameGrabber grabber = new OpenCVFrameGrabber(0);

    private boolean isCamAvailable = true;

    public CamUtil() {
        for (Webcam webcam : Webcam.getWebcams()) {
            System.out.println("Webcam detected: " + webcam.getName());
        }
        Webcam.addDiscoveryListener(this);
        try {
            grabber.start();
        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(CamUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void startRecording(String path, Long length, int fps) throws IOException {
        int frames = (int) (length / (1000 / fps));
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        for (int i = 1; i <= frames; i++) {
            try {
                long elapsed = System.currentTimeMillis();
                Frame frame = grabber.grab();
                IplImage img = converter.convert(frame);
                cvSaveImage(path + "\\image_" + i + ".png", img);
                elapsed = System.currentTimeMillis() - elapsed;
                long delay = 1000 / fps - elapsed;
                if (delay < 0) delay = 0;
                Thread.sleep(delay);
            } catch (FrameGrabber.Exception ex) {
                Logger.getLogger(CamUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(CamUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void closeCam() {
        if (grabber != null) {
            try {
                grabber.stop();
                grabber.release();
                grabber.close();
            } catch (FrameGrabber.Exception ex) {
                Logger.getLogger(CamUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
