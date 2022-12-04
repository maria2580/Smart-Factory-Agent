package org.primitive.SensorRelates;

import org.primitive.Network.Callretrofit;

import java.util.ArrayList;

public class Sensors {
    String ID;
    private ArrayList<Sensor> sensors;

    public Sensors(String ID){
        this.ID=ID;
        sensors = Callretrofit.get_all_sensor(ID);
    }
    public SensorValue[] getAllSensorValue(){
        SensorValue[] sensorValues=new SensorValue[sensors.size()];
        for(int i=0;i<sensors.size();i++){
            sensorValues[i]=new SensorValue(sensors.get(i).getName(),sensors.get(i).getValue());
        }
        return sensorValues;
    }

    public void print(){
        for (int i =0; i<sensors.size();i++){
            System.out.println(sensors.get(i).getIndex()+".센서명: "+sensors.get(i).getName()+", 명령어: "+sensors.get(i).getCommand());
        }
    }
    public void append(String name, String command, String user_id){
        Callretrofit.post_new_sensor(name, command,user_id);
        sensors=Callretrofit.get_all_sensor(ID);
    }
    public void update(Sensor sensor){
        Callretrofit.update_sensor(sensor);
        sensors=Callretrofit.get_all_sensor(ID);
    }
    public void delete(long index){
        Callretrofit.delete_sensor(index);
        sensors=Callretrofit.get_all_sensor(ID);
    }
}
