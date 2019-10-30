package com.axelfernandez.telteka.response;

import com.axelfernandez.telteka.R;
import com.axelfernandez.telteka.model.Category;
import com.axelfernandez.telteka.model.Registry;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegistryResponse {
    @SerializedName("registry")
    @Expose
    private List<Registry> registries;

    public List<Registry> getRegistries() {
        return registries;
    }

    public void setRegistries(List<Registry> registries) {
        this.registries = registries;
    }
}
