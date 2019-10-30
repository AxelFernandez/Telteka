package com.axelfernandez.telteka.ui.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.axelfernandez.telteka.model.Registry;
import com.axelfernandez.telteka.repository.DetailRepository;
import com.axelfernandez.telteka.repository.RegistryRepository;
import com.axelfernandez.telteka.response.RegistryResponse;


public class DetailViewModel extends AndroidViewModel {

    private DetailRepository detailRepository;
    private LiveData<RegistryResponse> detailResponse;
    private String idCategory;
    private Application application;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        this.application= application;



    }
    public LiveData<RegistryResponse> getDetailResponseLiveData() {
        return detailResponse;
    }
    public void init(){
        detailRepository = new DetailRepository(application,idCategory);
        detailResponse = detailRepository.getCategories();
    }
    public void setIdCategory(String idCategory){
        this.idCategory = idCategory;
    }



}
