package org.primitive.SensorRelates;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class Sensor {
    private long index;
    private String name;
    private String command;
    public Sensor(long index,String name, String command) {
        this.index=index;
        this.name = name;
        this.command = command;
    }
      public String getValue(){
        Cmd cmd = new Cmd();
        cmd.setCommand(this.command);
        String result = cmd.execCommand();
        return result;
    }

}
