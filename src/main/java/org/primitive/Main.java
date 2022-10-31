package org.primitive;

import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Factory Agnet program\nPrimitive_number_one\n");
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



}



