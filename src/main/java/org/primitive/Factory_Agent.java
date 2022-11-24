package org.primitive;

import org.primitive.Network.Callretrofit;
import org.primitive.SensorRelates.Sensor;
import org.primitive.SensorRelates.SensorValue;
import org.primitive.SensorRelates.Sensors;

import java.util.Scanner;

public class Factory_Agent {
    LoginToken loginToken;
    String ID;
    Sensors sensors;
    Factory_Agent(LoginToken loginToken,String ID){
        this.loginToken= loginToken;
        this.ID=ID;
        sensors=new Sensors(ID);
    }
    public void execute(){
//        Thread thread=new Thread(){
//            @Override
//            public void run() {
//                super.run();
                while (true) {
                    SensorValue[] sensorValues = sensors.getAllSensorValue();
                    for (int i = 0; i < sensorValues.length; i++) {
                        System.out.println(sensorValues[i].name + " : " + sensorValues[i].value);
                    }
                    //Callretrofit.post_sensors_data(sensors.getAllSensorValue(),ID,loginToken);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//            }
//        };
//        thread.start();

    }



}
