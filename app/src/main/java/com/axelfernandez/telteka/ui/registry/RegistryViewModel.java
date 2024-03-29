package com.axelfernandez.telteka.ui.registry;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.axelfernandez.telteka.repository.CategoryRepository;
import com.axelfernandez.telteka.repository.RegistryRepository;
import com.axelfernandez.telteka.response.CategoryResponse;
import com.axelfernandez.telteka.response.RegistryResponse;


public class RegistryViewModel extends AndroidViewModel {

    private RegistryRepository registryRepository;
    private LiveData<RegistryResponse> registryResponse;
    private String idCategory;
    private Application application;

    public RegistryViewModel(@NonNull Application application) {
        super(application);
        this.application= application;



    }
    public LiveData<RegistryResponse> getCategoryResponseLiveData() {
        return registryResponse;
    }
    public void init(){
        registryRepository = new RegistryRepository(application,idCategory);
        registryResponse = registryRepository.getRegistry();
    }
    public void setIdCategory(String idCategory){
        this.idCategory = idCategory;
    }



}
