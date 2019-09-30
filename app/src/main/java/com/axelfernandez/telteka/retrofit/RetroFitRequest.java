package com.axelfernandez.telteka.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitRequest {
    private static Retrofit retrofit;
    private static String BASE_URL = "http://www.telteka.com.ar/index.php/api/";

    public static Retrofit getInstance() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
