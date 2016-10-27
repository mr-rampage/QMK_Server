package com.intelliware.qmk.service.domain;

import org.springframework.util.StringUtils;

/**
 * Created by jr on 2016-10-27.
 */
public class KeyMapRequest {

    private KeyMap layer;

    public KeyMap getLayer() {
        return layer;
    }

    public void setLayer(KeyMap layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {

        return "KeyMapRequest{" +
                "layer=" + layer +
                '}';
    }
}
