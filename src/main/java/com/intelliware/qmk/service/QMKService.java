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

import java.io.IOException;

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

    @RequestMapping(method = POST)
    @ApiOperation("Generate KeyMap File")
    public ResponseEntity<String> generateKeyMapFile(@RequestBody KeyMapRequest keyMapRequest) throws IOException {

        logger.debug("Key Map:" + keyMapRequest.toString());
        String result = generator.generate(keyMapRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}