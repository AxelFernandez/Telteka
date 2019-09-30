package com.axelfernandez.telteka.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.axelfernandez.telteka.repository.CategoryRepository;
import com.axelfernandez.telteka.response.CategoryResponse;


public class MainViewModel extends AndroidViewModel {

    private CategoryRepository categoryRepository;
    private LiveData<CategoryResponse> categoryResponseLiveData;


    public MainViewModel(@NonNull Application application) {
        super(application);

        categoryRepository = new CategoryRepository(application);
        categoryResponseLiveData = categoryRepository.getCategories();

    }
    public LiveData<CategoryResponse> getCategoryResponseLiveData() {
        return categoryResponseLiveData;
    }




}
