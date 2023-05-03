package com.example.lksapps.API;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseurl = "http:10.0.2.2/apibaru/";
    private static Retrofit retro;

    public static Retrofit conRetro() {
        if(retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
