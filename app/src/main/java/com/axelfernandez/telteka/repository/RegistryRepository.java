package com.axelfernandez.telteka.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.axelfernandez.telteka.R;
import com.axelfernandez.telteka.response.CategoryResponse;
import com.axelfernandez.telteka.response.RegistryResponse;
import com.axelfernandez.telteka.retrofit.ApiRequest;
import com.axelfernandez.telteka.retrofit.RetroFitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistryRepository {
    private static final String TAG = RegistryRepository.class.getSimpleName();
    private ApiRequest apiRegistry;
    private String idCategory;
    private Context context;

    /**
     * This will be the code who decide if it will be to retrofit or Room
     */
    public RegistryRepository(Context context,String idCategory){
        this.idCategory = idCategory;
        this.context = context;
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            apiRegistry = RetroFitRequest.getInstance().create(ApiRequest.class);
        }
    }


    public LiveData<RegistryResponse> getRegistry(){
        final MutableLiveData<RegistryResponse> data = new MutableLiveData();
        apiRegistry.getRegistry(context.getResources().getString(R.string.registryfromCategory)+'/'+idCategory).enqueue(new Callback<RegistryResponse>() {
            @Override
            public void onResponse(Call<RegistryResponse> call, Response<RegistryResponse> response) {
                Log.d(TAG, "onResponse response:: " + response);
                Log.d(TAG, "onResponse Body:: " + response.body());
                if (response.body() != null){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegistryResponse> call, Throwable t) {
               Log.e(TAG,t.toString());
               Log.e(TAG,call.toString());
                data.setValue(null);
            }
        });

        return data;
    }
}
