package org.primitive.SensorRelates;

import java.io.Serializable;

public class Sensor implements Serializable {
    private long index;
    private String name;
    private String command;
    public Sensor(long index,String name, String command) {
        this.index=index;
        this.name = name;
        this.command = command;
    }
    public long getIndex() {
        return index;
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

        cmd.setCommand(this.command);
        String result = cmd.execCommand();

        System.out.println(result);
        return result;
    }

}
