package com.intelliware.qmk.util;

import java.io.*;

/**
 * Created by jr on 2016-10-27.
 */
public class IOUtil {

    public static String readFile(String fileName) {
        InputStream is = IOUtil.class.getClassLoader().getResourceAsStream(fileName);
        StringWriter sw = new StringWriter();
        try {
            org.apache.commons.io.IOUtils.copy(is, sw);
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file:" + fileName, e);
        }
    }

    public static File writeFile(String dirPath, String fileName, String content) {


        File dir = new File(dirPath);
        try {
            dir.mkdirs();
            File file = new File(dir, fileName);
            try(  PrintWriter out = new PrintWriter( file )  ){
                out.println( content );
            }

            return file;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to write file:" + dir.getAbsolutePath() + "/" + fileName, e);
        }
    }
}
