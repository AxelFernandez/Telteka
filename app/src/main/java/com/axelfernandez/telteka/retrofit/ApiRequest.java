package com.axelfernandez.telteka.retrofit;

import com.axelfernandez.telteka.response.CategoryResponse;
import com.axelfernandez.telteka.response.RegistryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiRequest {

    @GET
    Call<CategoryResponse> getCategories(@Url String url);

    @GET
    Call<RegistryResponse> getRegistry(@Url String url);

    @GET
    Call<RegistryResponse> getDetail(@Url String url);

}

