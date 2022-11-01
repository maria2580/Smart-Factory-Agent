package org.primitive;

import org.primitive.SensorRelates.Sensor;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        write_Sensor_object(new Sensor("온도센서 1","안녕하세요0 입력 ㅋdsss"));
        write_Sensor_object(new Sensor("온도센서 2","안녕하세요 1입력 ㅋdsss"));
        write_Sensor_object(new Sensor("온도센서 3","안녕하세요 입2력 ㅋdsss"));
        write_Sensor_object(new Sensor("온도센서 4","안녕하세요 입력3 ㅋdsss"));
        write_Sensor_object(new Sensor("온도센서 15","안녕하세요 입력4 ㅋdsss"));
        ArrayList<Sensor> sensors= read_Sensor_object();
        System.out.println("센서 읽기 동작:");
        for (int i=0;i<sensors.size();i++){
            Sensor sensor= sensors.get(i);
            System.out.println("name = " + sensor.getName()+", command = "+sensor.getCommand());
        }

        System.out.println("\n\nFactory Agnet program\nPrimitive_number_one\n");
        //check login or register
        LoginToken loginToken = new LoginToken("initiated");
        boolean flag=false;
        do {
            System.out.println("1. 로그인 2. 회원등록");
            String input0 = new Scanner(System.in).next();
            switch (input0) {
                case "1":
                    System.out.print("ID: ");
                    String input_ID = new Scanner(System.in).next();
                    System.out.print("PW: ");
                    String input_PW = new Scanner(System.in).next();
                    //loginToken = new LoginToken(check_login(input_ID, input_PW));
                    if (loginToken.getTokenValue().equals("wrong ID,PW")) {
                        System.out.println("로그인 실패, id와 비밀번호를 다시 확인해 주세요");
                    }
                    else {
                        flag=true;
                    }

                    break;
                case "2":
                    System.out.println("회원등록을 시작합니다. ID와 PW를 입력하세요.");
                    do {
                        if (loginToken.getTokenValue().equals("exist user")) {
                            System.out.println("sign up 실패. 같은 유저가 있습니다. 다시 입력하세요.\n");
                        }
                        System.out.print("ID: ");
                        String input_ID1 = new Scanner(System.in).next();
                        System.out.print("PW: ");
                        String input_PW2 = new Scanner(System.in).next();
                        // loginToken = new LoginToken(signUp_user(input_ID1, input_PW1));
                        if (!(loginToken.getTokenValue().equals("initiated")||loginToken.getTokenValue().equals("exist user"))) {
                            flag=true;
                        }
                    } while (loginToken.getTokenValue().equals("initiated")||loginToken.getTokenValue().equals("exist user"));
                    break;
            }
        }while (flag==true);

        do{clearConsole();//Todo 콘솔창 왜 안지워질까


            System.out.println("\n1. Agent 실행\n2. 환경 설정\n");
            System.out.print("input: ");
            String input = new Scanner(System.in).next();
            switch (input){
                case "1":

                    break;
                case "2":
                    System.out.println("1. 계정 정보\n2. 센서 추가/수정\n");
                    System.out.print("input: ");
                    String input2 = new Scanner(System.in).next();
                    switch (input2){
                        case "1"://Todo 계정 정보 선택시 동작 고려(최하순위)
                            break;
                        case "2"://Todo 센서 추가 및 수정 파일 읽기 쓰기 처리(객체 파일로 하는게 어떨까)
                            //추가인지 수정인지를 먼저 선택하게 한뒤
                            //두 경우 모두, 파일 없으면 생성, 있으면 열어서 전부 불러오기.

                            System.out.println("1.추가 2. 수정");

                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;

            }
        }while (true);
    }
    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
    
    //Todo write_sensor_object는 없는 파일 새로 생성할 때 사용. Rewrite 함수 만들어야하고 sensor객체 안에 있는 파일 명 활용하면 될듯 
    //Todo 파일 이름순 출력인데 센서 값 정렬 문제발생하는 줄 알았는데 아님. 순서는 클라이언트에서 정렬 순을 바꾸면 됨.   
    //Todo 그렇다면 sensor 객체안에 파일명. 이제 필요한가?고민 필요. 아마 없을 듯
    public static void write_Sensor_object(Sensor sensor) {
        String dir;
        if (System.getProperty("os.name").contains("Windows")) {
            dir = "D:\\\\smartFactory\\sensors\\";
        } else {
            dir = "/usr/smartFactory/sensors/";
        }

        File file_dir = new File(dir);
        if (!file_dir.exists()) {
            file_dir.mkdirs();
        }

        File file;//파일 객체를 만들고 실제로 있는 파일이라면 i를 높여가면서 만들 파일명을 바꿈.
        int i=0;
        do {
            i++;
            file=new File(dir+"sensors_data_"+i+".txt");
        }while(file.exists());
        //sensor.setFile_index("sensors_data_"+i+".txt");


        try {

            FileOutputStream fileStream = new FileOutputStream(file); // 파일에 쓰는 역할

            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(sensor);
            os.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static ArrayList<Sensor> read_Sensor_object(){
        String dir;
        ArrayList<Sensor> sensors= new ArrayList<>();
        if (System.getProperty("os.name").contains("Windows"))
        {
            dir="D:\\\\smartFactory\\sensors\\";
        }
        else
        {
            dir="/usr/smartFactory/sensors/";
        }

        File file_dir= new File(dir);
        if (!file_dir.exists()){
            file_dir.mkdirs();
        }
        File[] files = file_dir.listFiles();

        for (int i = 0 ; i< files.length;i++){
            try {
                FileInputStream fileStream = new FileInputStream(files[i]); // 직렬화해서 썼던 파일을 다시 읽오는 역할
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


}



