package com.axelfernandez.telteka.ui.detail;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.axelfernandez.telteka.R;
import com.axelfernandez.telteka.adapters.DetailAdapter;
import com.axelfernandez.telteka.model.Registry;
import com.axelfernandez.telteka.ui.registry.RegistryFragment;

public class DetailFragment extends Fragment {
    private DetailViewModel mViewModel;
    private View v;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private DetailAdapter adapter;
    private Registry registry;
    RecyclerView rv;
    public static RegistryFragment newInstance() {
        return new RegistryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.main_fragment, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String idCategory = bundle.getString(getString(R.string.categortySelected));
            mViewModel.setIdCategory(idCategory);

        }
        mViewModel.init();
        rv = v.findViewById(R.id.rv);
        progressBar = v.findViewById(R.id.progressCategory);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);

        adapter = new DetailAdapter(getContext(), registry);
        rv.setAdapter(adapter);
        if (adapter.getItemCount() == 0){
            getDetails();
        }else{
            progressBar.setVisibility(View.GONE);
        }


    }

    private void getDetails() {
        mViewModel.getDetailResponseLiveData().observe(getActivity(), registryResponse -> {
            if (registryResponse != null) {
                progressBar.setVisibility(View.GONE);
                Registry articles = registryResponse.getRegistries().get(0);
                registry = articles;
                adapter.notifyDataSetChanged();

            }
        });
    }


}
