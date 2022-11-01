package org.primitive;

public class LoginToken {
    private String Token;//Todo 로그인 토큰 주기적으로 교체
    LoginToken(String Token){
        this.Token=Token;
    }
    public synchronized void updateTokenValue(){

    }

    public synchronized String getTokenValue() {
        return Token;
    }
}
