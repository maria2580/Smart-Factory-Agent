package org.primitive.SensorRelates;

import org.primitive.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sensors {
    public ArrayList<Sensor> sensors;
    public Sensors(){
        sensors= read_Sensor_object();
        //Todo 파일 읽기 시도, 없으면 빈 파일 생성 있으면 열어서 센서 객체에 담기
    }
    public SensorValue[] getAllSensorValue(){
        SensorValue[] sensorValues=new SensorValue[sensors.size()];
        for(int i=0;i<sensors.size();i++){
            sensorValues[i]=new SensorValue();
            sensorValues[i].name=sensors.get(i).getName();
            sensorValues[i].value=sensors.get(i).getValue();
        }
        return sensorValues;
    }

    public static void write_Sensor_object(Sensor sensor) {
        String dir=Main.get_dir();

        File file_dir = new File(dir);
        if (!file_dir.exists()) {
            file_dir.mkdirs();
        }

        File file;//파일 객체를 만들고 실제로 있는 파일이라면 i를 높여가면서 만들 파일명을 바꿈.
        int i=0;
        do {
            i++;
            file=new File(dir+"sensors_data_"+String.format("%010d",i)+".txt");
        }while(file.exists());


        try {
            FileOutputStream fileStream = new FileOutputStream(file); // 파일에 쓰는 역할

            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(sensor);
            os.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static ArrayList<Sensor> read_Sensor_object(){//Todo callretorfit 요청할곳 sensor(index, name, command)로 구성된 객체 배열 반환 받기
        String dir=Main.get_dir();
        ArrayList<Sensor> sensors= new ArrayList<>();

        File file_dir= new File(dir);
        if (!file_dir.exists()){
            file_dir.mkdirs();
        }
        List<File> files = Arrays.asList(file_dir.listFiles());
        files.sort(Comparator.naturalOrder());//리눅스에서의 경우 변경시간순으로 리스트가 읽히는 것 같아서 파일 읽기시 리스트로 만들고 정렬한뒤 다른 작업수행

        for (int i = 0 ; i< files.size();i++){
            try {
                FileInputStream fileStream = new FileInputStream(files.get(i)); // 직렬화해서 썼던 파일을 다시 읽오는 역할
                ObjectInputStream is = new ObjectInputStream(fileStream); // 읽어온 직렬화된 내용을 역직렬화 하는 역할
                while(true){
                    try {
                        sensors.add((Sensor) is.readObject());
                    } catch (IOException e){
                        break;
                    } catch (ClassNotFoundException e){
                        e.printStackTrace();
                    }
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    //Todo 삭제 메서드 추가 하고 실행시 환경설정에서 센서 추가/수정/삭제가능하게 만들기

}
