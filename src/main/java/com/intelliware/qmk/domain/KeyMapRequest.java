package com.intelliware.qmk.domain;

/**
 * Created by jr on 2016-10-27.
 */
public class KeyMapRequest {


    private String[][] layer;

    public String[][] getLayer() {
        return layer;
    }

    public void setLayer(String[][] layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {

        return "KeyMapRequest{" +
                "layer=" + layer +
                '}';
    }
}
