package com.intelliware.qmk.service;

import com.google.common.io.Resources;
import com.intelliware.qmk.hexgen.HexGenerateService;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("Since15")
public class HexFileGenerator {

    public static void main(String[] args)
            throws
            InterruptedException,
            IOException {

        new HexGenerateService().generateHex("atreus", "string");
    }
}
