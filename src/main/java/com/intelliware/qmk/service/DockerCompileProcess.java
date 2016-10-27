import com.google.common.io.Resources;
import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerException;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("Since15")
public class DockerCompileProcess {

    public static void main(String[] args)
            throws DockerCertificateException,
            DockerException,
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
