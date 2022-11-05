package org.primitive.SensorRelates;

import java.io.Serializable;

public class Sensor implements Serializable {
    private String name;
    private String command="";

    public Sensor(String name, String command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    public String getValue(){
        Cmd cmd = new Cmd();

        String command = cmd.inputCommand(this.command);
        String result = cmd.execCommand(command);

        System.out.println(result);
        return result;
    }

}
