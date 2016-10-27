package com.intelliware.qmk.hexgen;

import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jr on 2016-10-27.
 */
@Component
public class HexGenerateService {

    private static final Logger logger = LoggerFactory.getLogger(HexGenerateService.class);

    public void generateHex(String keyboard, String fileName) throws IOException, InterruptedException {

        String dockerDirectoryWithBuildargs = Resources.getResource("qmk_firmware").getFile();

        Process dockerProcess = new ProcessBuilder(
                "/Applications/Docker.app/Contents/Resources/bin/docker",
                "run",
                "-e",
                "keymap=" + fileName,
                "-e",
                "keyboard=" + keyboard,
                "--rm",
                "-v",
                dockerDirectoryWithBuildargs + ":/qmk:rw",
                "edasque/qmk_firmware")
                .directory(new File(dockerDirectoryWithBuildargs))
                .start();


        BufferedReader reader =
                new BufferedReader(new InputStreamReader(dockerProcess.getInputStream()));
        String line;
        while ( (line = reader.readLine()) != null) {
            logger.debug(line);
            System.out.println(line);
        }

        int response = dockerProcess.waitFor();

        logger.debug("Generating: "  + fileName);

    }
}
