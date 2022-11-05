package org.primitive.SensorRelates;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cmd {

    private StringBuffer buffer;
    private Process process;
    private BufferedReader bufferedReader;
    private StringBuffer readBuffer;

    public String inputCommand(String cmd) {

        buffer = new StringBuffer();
        if (System.getProperty("os.name").contains("Windows")) {
            buffer.append("cmd.exe ");
            buffer.append("/c ");
        }
        else {
            buffer.append("/bin/sh ");
            buffer.append("-c ");
        }
        buffer.append(cmd);


        return buffer.toString();
    }

    public String execCommand(String cmd) {
        try {
            process = Runtime.getRuntime().exec(cmd);
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;
            readBuffer = new StringBuffer();
            process.waitFor();
            while((line = bufferedReader.readLine()) != null) {
                readBuffer.append(line);
                readBuffer.append("\n");
            }

            process.destroy();
            return readBuffer.toString();
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}