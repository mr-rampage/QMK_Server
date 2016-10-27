package com.intelliware.qmk.service.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jr on 2016-10-27.
 */
public class KeyMap {


    private String[][] maps;


    public String[][] getMaps() {
        return maps;
    }

    public void setMaps(String[][] maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "KeyMap{" +
                "maps=" + Arrays.toString(maps) +
                '}';
    }
}
