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
    public void post_sensors_data(@Body SensorValue[] sensorValues, @Body String ID, @Body LoginToken loginToken){
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
    public String post_login_request(@Body String ID,@Body String PW) {
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
    public String post_signUp_request(@Body String ID,@Body String PW) {
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



}
