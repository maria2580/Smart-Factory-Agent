package org.primitive;

import org.primitive.Network.Callretrofit;
import org.primitive.SensorRelates.Sensor;
import org.primitive.SensorRelates.Sensors;

import java.util.Scanner;

public class Factory_Agent {
    LoginToken loginToken;
    String ID;
    Sensors sensors;
    Factory_Agent(LoginToken loginToken,String ID){
        this.loginToken= loginToken;
        this.ID=ID;
        //Todo sensors 멤버변수에 파일 읽기로 저장된 센서 객체파일(이름,명령어 리스트 객체파일) 읽기로 초기화
    }
    public void execute(){
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Callretrofit.post_sensors_data(sensors.getAllSensorValue(),ID,loginToken);
            }
        };

    }



}
