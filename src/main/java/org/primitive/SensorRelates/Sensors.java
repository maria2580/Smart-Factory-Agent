package org.primitive.SensorRelates;

import org.primitive.Main;
import org.primitive.Network.Callretrofit;

import java.io.*;
import java.util.ArrayList;

public class Sensors {
    private ArrayList<Sensor> sensors;
    public int size(){
        return sensors.size();
    }
    public Sensor get(int i){
        return sensors.get(i);
    }
    public Sensors(String ID){
        sensors= Callretrofit.get_all_sensor(ID);
    }
    public SensorValue[] getAllSensorValue(){
        SensorValue[] sensorValues=new SensorValue[sensors.size()];
        for(int i=0;i<sensors.size();i++){
            sensorValues[i]=new SensorValue(sensors.get(i).getName(),sensors.get(i).getValue());
        }
        return sensorValues;
    }

    public static void write_Sensor_object(Sensor sensor) {
//        String dir=Main.get_dir();
//
//        File file_dir = new File(dir);
//        if (!file_dir.exists()) {
//            file_dir.mkdirs();
//        }
//
//        File file;//파일 객체를 만들고 실제로 있는 파일이라면 i를 높여가면서 만들 파일명을 바꿈.
//        int i=0;
//        do {
//            i++;
//            file=new File(dir+"sensors_data_"+String.format("%010d",i)+".txt");
//        }while(file.exists());
//
//
//        try {
//            FileOutputStream fileStream = new FileOutputStream(file); // 파일에 쓰는 역할
//
//            ObjectOutputStream os = new ObjectOutputStream(fileStream);
//            os.writeObject(sensor);
//            os.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }


    }

    public ArrayList<Sensor> getSensors(){
        return sensors;
    }
    public static void rewrite_Sensor_object(int index,Sensor sensor){
        String filename="sensors_data_"+String.format("%010d",index)+".txt";
        String dir=Main.get_dir();
        File file=new File(dir+filename);
        if(!file.exists()){
            return;
        }
        try {
            FileOutputStream fileStream = new FileOutputStream(file); // 파일에 쓰는 역할
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(sensor);
            os.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
