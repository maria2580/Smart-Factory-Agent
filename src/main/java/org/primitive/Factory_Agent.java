package org.primitive;

import org.primitive.Network.Callretrofit;
import org.primitive.SensorRelates.SensorValue;
import org.primitive.SensorRelates.Sensors;


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
                    Callretrofit.post_sensors_data(sensors.getAllSensorValue(),ID,loginToken.getTokenValue());
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
