package com.example.wamp.Network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("add")
    Call<ResponseBody> add(
            @Field("email") String email,
            @Field("name") String name,
            @Field("staff_id") String staff_id,
            @Field("department") String department
            );
}
