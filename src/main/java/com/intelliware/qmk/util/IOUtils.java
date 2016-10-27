package com.intelliware.qmk.util;

import java.io.*;

/**
 * Created by jr on 2016-10-27.
 */
public class IOUtils {

    public static String readFile(String fileName) {
        InputStream is = IOUtils.class.getClassLoader().getResourceAsStream(fileName);
        StringWriter sw = new StringWriter();
        try {
            org.apache.commons.io.IOUtils.copy(is, sw);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file:" + fileName, e);
        }
    }

    public static File writeFile(String filePath, String content) {
        try {
            File file = new File(filePath);
            try(  PrintWriter out = new PrintWriter( file )  ){
                out.println( content );
            }

            return file;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to write file:" + filePath, e);
        }
    }
}
