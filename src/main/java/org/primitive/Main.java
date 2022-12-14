package org.primitive;

import org.primitive.Network.Callretrofit;
import org.primitive.Network.LoginStatus;
import org.primitive.SensorRelates.Sensor;

import java.util.*;

public class Main {
    public static String user_id;
    public static Factory_Agent factory_agent;
    public static void main(String[] args) {
        System.out.println("\n\nFactory Agnet program\nPrimitive_number_one\n");

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
                    String result = Callretrofit.post_login_request(input_ID,input_PW);
                    if (result.equals(LoginStatus.DENIED+"")) {
                        System.out.println("로그인 실패, id와 비밀번호를 다시 확인해 주세요");
                    }
                    else if(result.equals(LoginStatus.PERMITTED+"")) {
                        user_id=input_ID;
                        flag=true;
                    }
                    break;
                case "2":
                    System.out.println("회원등록을 시작합니다. ID와 PW를 입력하세요.");
                    System.out.print("ID: ");
                    String input_ID1 = new Scanner(System.in).next();
                    System.out.print("PW: ");
                    String input_PW1 = new Scanner(System.in).next();
                    result = Callretrofit.post_signUp_request(input_ID1, input_PW1);
                    if (result.equals(LoginStatus.EXIST_ID+"")) {
                        System.out.println("sign up 실패. 같은 유저가 있습니다. 다시 입력하세요.\n");
                    }
                    if (result.equals(LoginStatus.PERMITTED+"")) {
                        user_id=input_ID1;
                        flag=true;
                    }
                    break;
            }
        }while (!flag);
        factory_agent = new Factory_Agent(user_id);//공장 객체 생성, 내부에 센서배열 객체 있음
        do{
            clearConsole();//Todo 콘솔창 왜 안지워질까
            System.out.println("\n1. Agent 실행\n2. 환경 설정\n");
            System.out.print("input: ");
            String input = new Scanner(System.in).next();
            switch (input){
                case "1":
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
                factory_agent.sensors.print();
                System.out.println("1.추가 2. 수정 3. 삭제" );
                String input3=new Scanner(System.in).next();
                String name;
                String command;
                switch (input3){

                    case "1":
                        System.out.println("센서명을 입력하세요(skip: quit)");
                        name = new Scanner(System.in).next();
                        if (name.equals("quit")){
                            break;
                        }
                        System.out.println("명령어을 입력하세요(skip: quit)");
                        command = new Scanner(System.in).next();
                        if (command.equals("quit")){
                            break;
                        }
                        factory_agent.sensors.append(name, command,user_id);
                        break;
                    case "2":
                        factory_agent.sensors.print();
                        System.out.println("수정할 센서의 인덱스를 선택하세요(skip: quit)");
                        index_s=new Scanner(System.in).next();
                        if (index_s.equals("quit")){
                            break;
                        }
                        System.out.println("수정할 이름을 입략히세요(skip: quit)");
                        name=new Scanner(System.in).next();
                        if (name.equals("quit")){
                            break;
                        }
                        System.out.println("수정할 명령어를 입력하세요(skip: quit)");
                        command=new Scanner(System.in).next();
                        if (command.equals("quit")){
                            break;
                        }
                        factory_agent.sensors.update(new Sensor(Long.parseLong(index_s),name,command));
                        break;
                    case "3":
                        factory_agent.sensors.print();
                        System.out.println("삭제할 센서의 인덱스를 선택하세요(skip: quit)");
                        index_s=new Scanner(System.in).next();
                        factory_agent.sensors.delete(Integer.parseInt(index_s));
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
        }catch (final Exception e) {}
    }
}



