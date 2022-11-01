package org.primitive;

import java.util.Scanner;

public class Factory_Agent {
    LoginToken loginToken;
    Factory_Agent(LoginToken loginToken){
        this.loginToken= loginToken;
    }
    public void execute(){
        //Todo 팩토리 Agent execute시 센서읽어들여서 센서 값 가져온뒤 처리하여 서버에 전송하는 동작 10초 쉬면서 반복
    }



}
