package com.intelliware.qmk.service.mapper;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliware.qmk.service.domain.KeyMapRequest;
import com.intelliware.qmk.service.user.QMKService;
import com.intelliware.qmk.service.util.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class KeyMapCGenerator {


    private static final Logger logger = LoggerFactory.getLogger(QMKService.class);

    public static final String KEY_MAP_CONFIG_FILE = "keymap.json";
    public static final String KEY_MAP_C_TEMPLATE = "keymap_template.c";
    public static final String CURL_BRACKET_LEFT = "{";
    public static final String CURL_BRACKET_RIGHT = "}";
    private Map<String, String> KEY_MAP_CONFIG;


    public KeyMapCGenerator() {
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

    public String generate(KeyMapRequest request) throws IOException {

        String template = Utils.readFile(KEY_MAP_C_TEMPLATE);

        String[][] source = request.getLayer();

        String mappedLayer1 = mapOneLayer(source);

        String generatedFile = template.replace("[[==KEY_MAP_0==]]", mappedLayer1);


        logger.debug(generatedFile);
        return generatedFile;
    }

    private String mapOneLayer(String[][] source) {
        StringBuilder strBuld = new StringBuilder();
        strBuld.append(CURL_BRACKET_LEFT);

        for (int i = 0; i < source.length; i++) {
//            target[i] = new String[];
            strBuld.append(CURL_BRACKET_LEFT);


            String[] strings = source[i];
            for (int j = 0; j < strings.length; j++) {
                if (j != 0) {
                    strBuld.append(", ");
                }
                strBuld.append(mapKey(source[i][j]));

//                target[i][j] = mapKey(source[i][j]);
            }
            strBuld.append(CURL_BRACKET_RIGHT);

        }
        strBuld.append(CURL_BRACKET_RIGHT);
        return strBuld.toString();
    }

    public String mapKey(String key) {
        return KEY_MAP_CONFIG.get(key.toUpperCase());
    }

}
