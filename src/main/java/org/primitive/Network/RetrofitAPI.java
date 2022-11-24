package org.primitive.Network;


import java.util.Locale;
import java.util.SplittableRandom;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import org.primitive.LoginToken;
import org.primitive.SensorRelates.Sensor;
import org.primitive.SensorRelates.SensorValue;
import org.primitive.SensorRelates.Sensors;
import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitAPI {

@POST("sensor_data") public Call<String> post_Sensors_data(@Body SensorValue[] sensorValues, @Body String ID, @Body LoginToken loginToken);
@POST ("login")public Call<String> post_login_request(@Body String ID, @Body String PW);
@POST ("sign_up") public Call<String> post_signUp_request(@Body String ID, @Body String PW);

@POST("sensors")public Call<String> post_new_sensor(@Body String name, @Body String command, @Body String id);
@GET("sensors")public Call<Sensor[]> get_all_sensor(@Body String id);
@PATCH("sensors")public Call<String> update_sensor(@Body Sensor sensor);
@DELETE("sensors")public Call<String>delete_sensor(@Body Sensor sensor);

}