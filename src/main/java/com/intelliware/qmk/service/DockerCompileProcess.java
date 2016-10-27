package com.intelliware.qmk.service;

import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("Since15")
public class DockerCompileProcess {

    public static void main(String[] args)
            throws
            InterruptedException,
            IOException {

        String dockerDirectoryWithBuildargs = Resources.getResource("qmk_firmware").getFile();

        Process dockerProcess = new ProcessBuilder(
                "/usr/bin/docker",
                "run",
                "-e",
                "keymap=default",
                "-e",
                "keyboard=preonic",
                "--rm",
                "-v",
                dockerDirectoryWithBuildargs + ":/qmk:rw",
                "edasque/qmk_firmware")
                .directory(new File(dockerDirectoryWithBuildargs))
                .redirectError(new File(Resources.getResource("error").getFile()))
                .redirectOutput(new File(Resources.getResource("out").getFile()))
                .start();

        dockerProcess.waitFor();
    }
}
