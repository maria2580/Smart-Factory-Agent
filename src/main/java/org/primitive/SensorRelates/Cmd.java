package org.primitive.SensorRelates;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Cmd {
    private String[] command;
    private Process process;
    private BufferedReader bufferedReader;
    private StringBuffer readBuffer;

    public void setCommand(String cmd) {
        command=new String[]{cmd};
    }

    public synchronized String execCommand() {
        try {
            process = Runtime.getRuntime().exec(command);

            System.out.println("process : "+process.pid() +" info : "+process.info()+" info : "+process.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;
            readBuffer = new StringBuffer();

            while((line = bufferedReader.readLine()) != null) {
                readBuffer.append(line);


                System.out.println(line);
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