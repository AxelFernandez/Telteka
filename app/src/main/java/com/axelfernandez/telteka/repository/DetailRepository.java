package com.axelfernandez.telteka.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.axelfernandez.telteka.R;
import com.axelfernandez.telteka.model.Registry;
import com.axelfernandez.telteka.response.RegistryResponse;
import com.axelfernandez.telteka.retrofit.ApiRequest;
import com.axelfernandez.telteka.retrofit.RetroFitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRepository {
    private static final String TAG = DetailRepository.class.getSimpleName();
    private ApiRequest apiDetail;
    private String idRegistry;
    private Context context;

    /**
     * This will be the code who decide if it will be to retrofit or Room
     */
    public DetailRepository(Context context,String idRegistry){
        this.idRegistry = idRegistry;
        this.context = context;
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            apiDetail = RetroFitRequest.getInstance().create(ApiRequest.class);
        }
    }


    public LiveData<RegistryResponse> getCategories(){
        final MutableLiveData<RegistryResponse>data = new MutableLiveData();
        apiDetail.getDetail(context.getResources().getString(R.string.registryFromId)+ "/"+ idRegistry).enqueue(new Callback<RegistryResponse>() {
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
