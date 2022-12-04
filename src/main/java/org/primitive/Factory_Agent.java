package org.primitive;

import org.primitive.Network.Callretrofit;
import org.primitive.SensorRelates.Sensors;


public class Factory_Agent {

    String ID;
    Sensors sensors;
    Factory_Agent(String ID){

        this.ID=ID;
        sensors=new Sensors(ID);
    }
    public void execute(){
        while (true) {
            Callretrofit.post_sensors_data(sensors.getAllSensorValue(),ID);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
