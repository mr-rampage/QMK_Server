package com.intelliware.qmk.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by jr on 2016-10-27.
 */
public class Utils {

    public static String readFile(String fileName) {
        InputStream is = Utils.class.getClassLoader().getResourceAsStream(fileName);
        StringWriter sw = new StringWriter();
        try {
            IOUtils.copy(is, sw);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file:" + fileName, e);
        }

    }
}
