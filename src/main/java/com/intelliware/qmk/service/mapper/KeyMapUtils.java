package com.intelliware.qmk.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jr on 2016-10-27.
 */
public class KeyMapUtils {



    public static final String KEY_MAP_CONFIG = "keymap.json";

    Map<String, String> getKeyMap() throws IOException {
        String keyMapConfig = FileUtils.readFileToString(new File(KEY_MAP_CONFIG));

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(keyMapConfig, HashMap.class);
    }
}
