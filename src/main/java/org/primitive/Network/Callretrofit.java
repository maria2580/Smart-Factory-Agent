package org.primitive.Network;

import org.primitive.LoginToken;
import org.primitive.SensorRelates.SensorValue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;

import java.io.IOException;

public class Callretrofit {
    public static void post_sensors_data(SensorValue[] sensorValues, String ID, LoginToken loginToken){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_Sensors_data(sensorValues,ID,loginToken);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("post_sensors_data.onResponse");

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("post_sensors_data.onFailure");
            }
        });
    }
    public static String post_login_request(String ID, String PW) {
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_login_request(ID,PW);
        String response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static String post_signUp_request(String ID,String PW) {
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_signUp_request(ID,PW);
        String response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    //Todo 서버에 sensor테이블 CRUD 만들고 각각에 맞는 레트로핏 요청 만들기
    //센서 리스트 get은 Sensors 생성자에서 호출하기
    //센서 객체 필드들은 서버에 있는 sensorDAO 필드랑 똑같이 만들기
    //update는 기존에 있던 센서 이름이나 명령어 수정이고
    //post는 새로운 센서 추가 명령이고
    //delete 메서드로 서버에 있는 명령어 삭제


}
