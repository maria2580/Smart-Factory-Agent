package org.primitive.Network;


import org.primitive.Network.VO.LoginVO;
import org.primitive.Network.VO.SensorDTO;
import org.primitive.Network.VO.SensorValueDTO;
import org.primitive.Network.VO.SignUpVO;
import org.primitive.SensorRelates.Sensor;
import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitAPI {

@POST("sensors_value") Call<String> post_Sensors_data(@Body SensorValueDTO sensorValueDTO);
@POST ("login") Call<String> post_login_request(@Body LoginVO loginVO);
@POST ("sign_up") Call<String> post_signUp_request(@Body SignUpVO signUpVO);

@POST("sensors") Call<String> post_new_sensor(@Body SensorDTO sensorDTO);
@GET("sensors/{ID}") Call<Sensor[]> get_all_sensor(@Path("ID") String id);
@PATCH("sensors") Call<String> update_sensor(@Body Sensor sensor);
@DELETE("sensors/{index}") Call<String>delete_sensor(@Path("index") long index);

}