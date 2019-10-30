package com.axelfernandez.telteka.ui.registry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.axelfernandez.telteka.R;
import com.axelfernandez.telteka.adapters.RegistryAdapter;
import com.axelfernandez.telteka.interfaces.IOnBackPressed;
import com.axelfernandez.telteka.model.Registry;

import java.util.ArrayList;
import java.util.List;

public class RegistryFragment extends Fragment implements IOnBackPressed {

    private RegistryViewModel mViewModel;
    private View v;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private RegistryAdapter adapter;
    private ArrayList<Registry> registry = new ArrayList<>();
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
        mViewModel = ViewModelProviders.of(this).get(RegistryViewModel.class);
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

        adapter = new RegistryAdapter(getContext(), registry);
        rv.setAdapter(adapter);
        if (adapter.getItemCount() == 0){
            getRegistry();
        }else{
            progressBar.setVisibility(View.GONE);
        }


    }

    private void getRegistry() {
        mViewModel.getCategoryResponseLiveData().observe(getActivity(), registryResponse -> {
            if (registryResponse != null) {
                progressBar.setVisibility(View.GONE);
                List<Registry> articles = registryResponse.getRegistries();
                registry.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-2).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        return true;
    }
}
