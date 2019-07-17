package com.example.wamp.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class ApiClient {
        private static Retrofit retrofit;

        public static Retrofit getRetrofit(){
            if (retrofit==null){
                Retrofit.Builder builder=new Retrofit.Builder()
                        .baseUrl("http://localhost:5000/MyApi/public/")
                        .addConverterFactory(GsonConverterFactory.create());
                retrofit=builder.build();
            }
            return retrofit;
        }
    }
