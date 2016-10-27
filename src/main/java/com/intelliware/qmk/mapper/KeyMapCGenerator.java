package com.intelliware.qmk.mapper;


import com.intelliware.qmk.domain.KeyMapRequest;
import com.intelliware.qmk.hexgen.HexGenerateService;
import com.intelliware.qmk.service.QMKService;
import com.intelliware.qmk.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class KeyMapCGenerator {


    private static final Logger logger = LoggerFactory.getLogger(QMKService.class);

    public static final String KEY_MAP_C_TEMPLATE = "keymap_template.c";
    public static final String KEYBOARD = "atreus";
    public static final String KEY_MAP_C_GEN_DIR_PATH = "classes/qmk_firmware/keyboards/" + KEYBOARD + "/keymaps";
    public static final String KEY_MAP_BUILD_DIR_PATH= "classes/qmk_firmware/.build";


    @Autowired
    private HexGenerateService hexGen;

    public String generate(KeyMapRequest request) throws IOException {

        String template = IOUtil.readFile(KEY_MAP_C_TEMPLATE);

        String[][] source = request.getLayer();

        String mappedLayer1 = mapOneLayer(source);

        String generatedFile = template.replace("[[==KEY_MAP_0==]]", mappedLayer1);



        return generatedFile;
    }

    public File generateFile(KeyMapRequest request) throws IOException, InterruptedException {
        String content = generate(request);

        logger.debug(content);
        File file = IOUtil.writeFile(KEY_MAP_C_GEN_DIR_PATH + '/' + request.getId(), "keymap.c", content);
        logger.debug("Generated File: " + file.getAbsolutePath());

        hexGen.generateHex(KEYBOARD, request.getId());

        return new File(KEY_MAP_BUILD_DIR_PATH, KEYBOARD + "_" + request.getId() + ".hex");
    }

    public static File getHexFileById(String id) {
        return new File(KEY_MAP_BUILD_DIR_PATH, KEYBOARD + "_" + id + ".hex");
    }

    private String mapOneLayer(String[][] source) {

        MappedLayer mappedLayer = new MappedLayer();
        mappedLayer.populateKeys(source);

        return mappedLayer.generateMappedString();

    }


}
