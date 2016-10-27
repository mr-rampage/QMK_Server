package com.intelliware.qmk.service.user;

import com.intelliware.qmk.service.domain.KeyMap;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * The QMK API
 */
//@CrossOrigin
@RestController
@RequestMapping("/qmk")
public class QMKService {

    private static final Logger logger = LoggerFactory.getLogger(QMKService.class);

    @RequestMapping(method = POST)
    @ApiOperation("Generate KeyMap File")
    public ResponseEntity<KeyMap> generateKeyMapFile(@RequestBody KeyMap keyMap) {

        logger.debug("Key Map:" + keyMap.toString());

        return new ResponseEntity<>(keyMap, HttpStatus.OK);
    }

}