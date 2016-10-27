package com.intelliware.qmk.service.mapper;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliware.qmk.service.domain.KeyMapRequest;
import com.intelliware.qmk.service.user.QMKService;
import com.intelliware.qmk.service.util.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class KeyMapCGenerator {


    private static final Logger logger = LoggerFactory.getLogger(QMKService.class);

    public static final String KEY_MAP_CONFIG = "keymap.json";


    public Map<String, Object> getKeyMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Utils.readFile(KEY_MAP_CONFIG), HashMap.class);
    }

    public String generate(KeyMapRequest request) throws IOException {

//        logger.debug(this.getKeyMap());

//        File file = new File("keymap_template.c");
//        String string = FileUtils.readFileToString(file);
//        System.out.println("Read in: " + string);

        return this.getKeyMap().toString();

    }
}
