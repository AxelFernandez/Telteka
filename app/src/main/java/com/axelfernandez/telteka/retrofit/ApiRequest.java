package com.axelfernandez.telteka.retrofit;

import com.axelfernandez.telteka.model.Category;
import com.axelfernandez.telteka.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("categories/")
    Call<CategoryResponse> getCategories();
}
