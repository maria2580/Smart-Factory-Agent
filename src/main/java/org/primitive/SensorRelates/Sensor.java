package org.primitive.SensorRelates;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class Sensor implements Serializable {
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

        System.out.println(result);
        return result;
    }

}
