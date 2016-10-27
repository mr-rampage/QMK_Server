package com.intelliware.qmk.mapper;


import com.intelliware.qmk.domain.KeyMapRequest;
import com.intelliware.qmk.service.QMKService;
import com.intelliware.qmk.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KeyMapCGenerator {


    private static final Logger logger = LoggerFactory.getLogger(QMKService.class);

    public static final String KEY_MAP_C_TEMPLATE = "keymap_template.c";
    public static final String CURL_BRACKET_LEFT = "{";
    public static final String CURL_BRACKET_RIGHT = "}";


    public String generate(KeyMapRequest request) throws IOException {

        String template = Utils.readFile(KEY_MAP_C_TEMPLATE);

        String[][] source = request.getLayer();

        String mappedLayer1 = mapOneLayer(source);

        String generatedFile = template.replace("[[==KEY_MAP_0==]]", mappedLayer1);


        logger.debug(generatedFile);
        return generatedFile;
    }

    private String mapOneLayer(String[][] source) {

        MappedLayer mappedLayer = new MappedLayer();
        mappedLayer.populateKeys(source);

        return mappedLayer.generateMappedString();

    }


}
