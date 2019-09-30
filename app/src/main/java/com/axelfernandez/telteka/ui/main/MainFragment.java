package com.axelfernandez.telteka.ui.main;

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
        rv = v.findViewById(R.id.rv);
        progressBar = v.findViewById(R.id.progressCategory);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);

        adapter = new CategoryAdapter(getContext(), categories);
        rv.setAdapter(adapter);
        getMovieArticles();

    }
    private void getMovieArticles() {
        mViewModel.getCategoryResponseLiveData().observe(getActivity(), categoryResponse -> {
            if (categoryResponse != null) {
                progressBar.setVisibility(View.GONE);
                List<Category> articles = categoryResponse.getCategories();
                categories.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
