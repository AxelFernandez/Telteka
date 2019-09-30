package com.axelfernandez.telteka.retrofit;

import com.axelfernandez.telteka.response.RegistryResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiRegistry {


    @GET
    public List<RegistryResponse> getRegistry(@Url String url);
}
