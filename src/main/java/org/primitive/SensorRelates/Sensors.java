package org.primitive.SensorRelates;

import java.util.ArrayList;

public class Sensors {
    private ArrayList<Sensor> sensors;
    Sensors(){
        //Todo 파일 읽기 시도, 없으면 빈 파일 생성 있으면 열어서 센서 객체에 담기
    }
    public SensorValue[] getAllSensorValue(){
        SensorValue[] sensorValues=new SensorValue[sensors.size()];
        for(int i=0;i<sensors.size();i++){
            sensorValues[i].name=sensors.get(i).getName();
            sensorValues[i].value=sensors.get(i).getValue();
        }
        return sensorValues;
    }

}
