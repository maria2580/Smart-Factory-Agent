package org.primitive;

import org.primitive.Network.Callretrofit;
import org.primitive.SensorRelates.Sensor;
import org.primitive.SensorRelates.Sensors;
import org.w3c.dom.ls.LSException;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    public static String user_id;
    public static Sensors sensors;
    public static void main(String[] args) {

        /*//파일 입출력 테스트 코드
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

        rewrite_Sensor_object(1,new Sensor("2222","@222222가나ab33"));
        rewrite_Sensor_object(3,new Sensor("2222","@222222가나ab33"));
        rewrite_Sensor_object(5,new Sensor("2222","@222222가나ab33"));
        rewrite_Sensor_object(7,new Sensor("2222","@222222가나ab33"));
        sensors= read_Sensor_object();
        System.out.println("센서 읽기 재동작:");
        for (int i=0;i<sensors.size();i++){
            Sensor sensor= sensors.get(i);
            System.out.println("name = " + sensor.getName()+", command = "+sensor.getCommand());
        }*/
        //피일 입출력 테스트 코드
        System.out.println("\n\nFactory Agnet program\nPrimitive_number_one\n");
        //check login or register
        LoginToken loginToken = new LoginToken("initiated");
        String ID="default";
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
                        ID=input_ID1;
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
        sensors=new Sensors(user_id);//센서값 읽어오기;
        do{
            clearConsole();//Todo 콘솔창 왜 안지워질까
            System.out.println("\n1. Agent 실행\n2. 환경 설정\n");
            System.out.print("input: ");
            String input = new Scanner(System.in).next();
            switch (input){
                case "1":
                    Factory_Agent factory_agent = new Factory_Agent(loginToken,ID);
                    factory_agent.execute();
                    break;
                case "2":
                    setting_agent();
                    break;
                default:
                    break;

            }
        }while (true);
    }
    private static void setting_agent(){
        String index_s;

        System.out.println("1. 계정 정보\n2. 센서 추가/수정\n");
        System.out.print("input: ");
        String input = new Scanner(System.in).next();
        switch (input){
            case "1"://Todo 계정 정보 선택시 동작 고려(최하순위)
                break;
            case "2":
                //추가인지 수정인지를 먼저 선택하게 한뒤
                //두 경우 모두, 파일 없으면 생성, 있으면 열어서 전부 불러오기.

                System.out.println("1.추가 2. 수정 3. 삭제" );
                String input3=new Scanner(System.in).next();
                String name;
                String command;
                switch (input3){

                    case "1":
                        System.out.println("센서명을 입력하세요");
                        name = new Scanner(System.in).next();
                        System.out.println("명령어을 입력하세요");
                        command = new Scanner(System.in).next();
                        Callretrofit.post_new_sensor(name, command,user_id);
                        break;
                    case "2":
                        for (int i =0; i<sensors.size();i++){
                            System.out.println(sensors.get(i).getIndex()+".센서명: "+sensors.get(i).getName()+", 명령어: "+sensors.get(i).getCommand());
                        }
                        System.out.println("수정할 센서의 인덱스를 선택하세요(skip: quit)");
                        index_s=new Scanner(System.in).next();
                        System.out.println("수정할 이름을 입략히세요(skip: quit)");
                        name=new Scanner(System.in).next();
                        System.out.println("수정할 명령어를 입력하세요(skip: quit)");
                        command=new Scanner(System.in).next();
                        if (index_s.equals("quit")|| name.equals("quit")||command.equals("quit")){
                            break;
                        }
                        Callretrofit.update_sensor(new Sensor(Integer.parseInt(index_s),name,command));
                        break;
                    case "3":
                        for (int i =0; i<sensors.size();i++){
                            System.out.println(sensors.get(i).getIndex()+".센서명: "+sensors.get(i).getName()+", 명령어: "+sensors.get(i).getCommand());
                        }
                        System.out.println("삭제할 센서의 인덱스를 선택하세요(skip: quit)");
                        index_s=new Scanner(System.in).next();
                        Callretrofit.delete_sensor(sensors.get(Integer.parseInt(index_s)));
                }
                break;
            default:
                break;
        }
    }
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            }else {
                Runtime.getRuntime().exec("clear");
            }
        }catch (final Exception e) {
            //  Handle any exceptions.
        }
    }
    public static String get_dir(){
        String dir;
        if (System.getProperty("os.name").contains("Windows")) {
            dir = "D:\\\\smartFactory\\sensors\\";
        } else {
            dir = "smartFactory/sensors/";
        }
        return dir;
    }



}



