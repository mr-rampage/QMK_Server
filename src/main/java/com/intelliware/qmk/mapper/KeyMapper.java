package com.intelliware.qmk.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliware.qmk.util.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jr on 2016-10-27.
 */
public class KeyMapper {

    public static final String KC_TRNS = "KC_TRNS";

    public static final String KEY_MAP_CONFIG_FILE = "keymap.json";

    private Map<String, String> KEY_MAP_CONFIG;

    public static KeyMapper instance = new KeyMapper();


    public static KeyMapper getInstance() {
        return instance;
    }

    private KeyMapper() {
        KEY_MAP_CONFIG = this.getKeyMap();
    }


    private Map<String, String> getKeyMap()  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String content = Utils.readFile(KEY_MAP_CONFIG_FILE);
            TypeReference<HashMap<String, String>> typeRef
                    = new TypeReference<HashMap<String, String>>() {};

            return mapper.readValue(content, typeRef);
        } catch (IOException e) {
            throw new RuntimeException("failed to load key map config file", e);
        }
    }


    public String mapKey(String key) {
        return KEY_MAP_CONFIG.get(key.toUpperCase());
    }
}
