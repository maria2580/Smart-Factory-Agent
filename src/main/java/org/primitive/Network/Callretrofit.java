package org.primitive.Network;

import org.primitive.Network.VO.LoginVO;
import org.primitive.Network.VO.SensorDTO;
import org.primitive.Network.VO.SensorValueDTO;
import org.primitive.Network.VO.SignUpVO;
import org.primitive.SensorRelates.Sensor;
import org.primitive.SensorRelates.SensorValue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Callretrofit {
    public static void post_sensors_data(SensorValue[] sensorValues, String ID, String loginToken){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_Sensors_data(new SensorValueDTO(sensorValues,ID,loginToken));
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
        Call<String> call = service.post_login_request(new LoginVO(ID,PW,Env.FACTORY));
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
        Call<String> call = service.post_signUp_request(new SignUpVO(ID,PW));
        String response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }

    public static String post_new_sensor(String name, String command,String id){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.post_new_sensor(new SensorDTO(name,command,id));
        String response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static ArrayList<Sensor> get_all_sensor(String id){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<Sensor[]> call = service.get_all_sensor(id);
        ArrayList<Sensor> response = null;
        try {
            response= (ArrayList<Sensor>) Arrays.asList(call.execute().body());

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static String update_sensor(Sensor sensor){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.update_sensor(sensor);
        String response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
    public static String delete_sensor(Sensor sensor){
        Retrofit retrofit = RetrofitClient.getInstance();
        RetrofitAPI service= retrofit.create(RetrofitAPI.class);
        Call<String> call = service.delete_sensor(sensor);
        String response = null;
        try {
            response= call.execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
}
