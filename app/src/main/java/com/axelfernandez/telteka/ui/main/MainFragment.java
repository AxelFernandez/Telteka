package com.axelfernandez.telteka.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.axelfernandez.telteka.R;
import com.axelfernandez.telteka.adapters.CategoryAdapter;
import com.axelfernandez.telteka.model.Category;
import com.axelfernandez.telteka.ui.registry.RegistryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private View v;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private CategoryAdapter adapter;
    private ArrayList<Category> categories = new ArrayList<>();
    RecyclerView rv;
    private String categorySelected;
    public static MainFragment newInstance() {
        return new MainFragment();
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
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            categorySelected= bundle.getString(getString(R.string.categortySelected));
        }else {
            categorySelected = "0";
        }
        mViewModel.init(categorySelected);
        rv = v.findViewById(R.id.rv);
        progressBar = v.findViewById(R.id.progressCategory);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);

        adapter = new CategoryAdapter(getContext(), categories);
        rv.setAdapter(adapter);
        if (adapter.getItemCount() == 0){
            getMovieArticles();
        }else{
            progressBar.setVisibility(View.GONE);
        }


    }
    private void getMovieArticles() {
        mViewModel.getCategoryResponseLiveData().observe(getActivity(), categoryResponse -> {
            if (categoryResponse != null) {
                if (categoryResponse.isHasChildren() == "true") {
                    progressBar.setVisibility(View.GONE);
                    List<Category> articles = categoryResponse.getCategories();
                    categories.addAll(articles);
                    adapter.notifyDataSetChanged();
                }else{
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        RegistryFragment registryFragment = new RegistryFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString(activity.getResources().getString(R.string.categortySelected),categorySelected);
                        registryFragment.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, registryFragment).addToBackStack(null).commit();



                }
            }
        });
    }


}
