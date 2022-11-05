package org.primitive.Network;


import java.util.Locale;
import java.util.SplittableRandom;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import org.primitive.LoginToken;
import org.primitive.SensorRelates.SensorValue;
import org.primitive.SensorRelates.Sensors;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitAPI {

@POST("Sensors") public Call<String> post_Sensors_data(@Body SensorValue[] sensorValues, @Body String ID, @Body LoginToken loginToken);
@POST ("login")public Call<String> post_login_request(@Body String ID, @Body String PW);
@POST("sign_up") public Call<String> post_signUp_request(@Body String ID, @Body String PW);

}