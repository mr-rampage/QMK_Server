package com.intelliware.qmk.service;

import com.intelliware.qmk.domain.KeyMapRequest;
import com.intelliware.qmk.mapper.KeyMapCGenerator;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * The QMK API
 */
//@CrossOrigin
@RestController
@RequestMapping("/qmk")
public class QMKService {

    private static final Logger logger = LoggerFactory.getLogger(QMKService.class);

    @Autowired
    private KeyMapCGenerator generator;

    @RequestMapping(value = "/mapkeys", method = POST)
    @ApiOperation("Generate KeyMap")
    public ResponseEntity<String> generateKeyMap(@RequestBody KeyMapRequest keyMapRequest,

                                                     HttpServletResponse response) throws IOException {

        logger.debug("Key Map:" + keyMapRequest.toString());
        String result = generator.generate(keyMapRequest);


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/mapkeys/file", method = POST)
    @ApiOperation("Generate KeyMap File Content")
    public ResponseEntity<Void> generateKeyMapFile(@RequestBody KeyMapRequest keyMapRequest,

                                                     HttpServletResponse response) throws IOException, InterruptedException {

        logger.debug("Key Map:" + keyMapRequest.toString());
        File result = generator.generateFile(keyMapRequest);

        response.setContentType("application/octet-stream");

        // get your file as InputStream
        InputStream is = new FileInputStream(result);
        org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/files/{session_id}", method = RequestMethod.GET)
    public ResponseEntity<Void>  getFile(
            @PathVariable("session_id") String sessionId,
            HttpServletResponse response) {

        try {
            File hexFile = KeyMapCGenerator.getHexFileById(sessionId);
            logger.debug("Getting file: " + hexFile.getAbsolutePath());
            if (!hexFile.exists()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // get your file as InputStream
            InputStream is = new FileInputStream(hexFile);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (IOException ex) {
            throw new RuntimeException("Error getting file content for session id {}" + sessionId, ex);
        }

    }

}