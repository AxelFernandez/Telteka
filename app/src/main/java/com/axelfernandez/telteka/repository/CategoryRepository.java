package com.axelfernandez.telteka.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.axelfernandez.telteka.model.Category;
import com.axelfernandez.telteka.response.CategoryResponse;
import com.axelfernandez.telteka.retrofit.ApiRequest;
import com.axelfernandez.telteka.retrofit.RetroFitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private static final String TAG = CategoryRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    /**
     * This will be the code who decide if it will be to retrofit or Room
     */
    public CategoryRepository(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected){
            apiRequest = RetroFitRequest.getInstance().create(ApiRequest.class);
        }
    }


    public LiveData<CategoryResponse> getCategories(){
        final MutableLiveData<CategoryResponse> data = new MutableLiveData();
        apiRequest.getCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.d(TAG, "onResponse response:: " + response);
                if (response.body() != null){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

    return data;
    }
}
