/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;

/**
 *
 * @author Scrip0
 */
public class MicUtil {

    private File file;
    private String soundFileName;
    private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    private int MONO = 1;
    private AudioFormat format = new AudioFormat(16000, 16, MONO, true, true);
//            AudioFormat.Encoding.PCM_SIGNED, 16000, 16, MONO, 2, 16000, true);
    private TargetDataLine mike;

    private void startAudioRecording(Long length) {
        new Thread() {
            public void run() {
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                if (!AudioSystem.isLineSupported(info)) {
                    JOptionPane.showMessageDialog(null, "Line not supported"
                            + info, "Line not supported",
                            JOptionPane.ERROR_MESSAGE);
                }
                try {
                    mike = (TargetDataLine) AudioSystem.getLine(info);
                    mike.open(format, mike.getBufferSize());
                    AudioInputStream sound = new AudioInputStream(mike);
                    mike.start();
                    delay(length);
                    AudioSystem.write(sound, fileType, file);
                } catch (LineUnavailableException ex) {
                    JOptionPane.showMessageDialog(null, "Line not available"
                            + ex, "Line not available",
                            JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "I/O Error " + ex,
                            "I/O Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.start();
    }

    private void stopRecording() {
        mike.stop();
        mike.close();
    }

    public void startRecording(String path, Long length) {
        soundFileName = path;
        file = new File(soundFileName);
        startAudioRecording(length);
    }

    private void delay(Long length) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                stopRecording();
            }
        };

        scheduler.schedule(r, length + 300, TimeUnit.MILLISECONDS);
    }

    public boolean isMicAvailable() {
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            return false;
        } else {
            return true;
        }
    }
}
