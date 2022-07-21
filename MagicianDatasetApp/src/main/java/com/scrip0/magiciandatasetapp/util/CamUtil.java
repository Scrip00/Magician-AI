/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrip0.magiciandatasetapp.util;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;

/**
 *
 * @author Scrip0
 */
public class CamUtil implements WebcamDiscoveryListener {

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
}
