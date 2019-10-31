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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.axelfernandez.telteka.R;
import com.axelfernandez.telteka.adapters.DetailAdapter;
import com.axelfernandez.telteka.model.Registry;
import com.axelfernandez.telteka.ui.registry.RegistryFragment;
import com.squareup.picasso.Picasso;

import org.apache.commons.text.StringEscapeUtils;

public class DetailFragment extends Fragment {
    private DetailViewModel mViewModel;
    private View v;
    private ProgressBar progressBar;
    private ImageView imageView;
    private TextView title;
    private TextView body;

      
    public static RegistryFragment newInstance() {
        return new RegistryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.detail_fragment, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        imageView = v.findViewById(R.id.app_bar_image);
        title = v.findViewById(R.id.detailTitle);
        body = v.findViewById(R.id.detailBody);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String idCategory = bundle.getString(getString(R.string.categortySelected));
            mViewModel.setIdCategory(idCategory);

        }
        mViewModel.init();
        progressBar = v.findViewById(R.id.progressCategory);

        getDetails();



    }

    private void getDetails() {
        mViewModel.getDetailResponseLiveData().observe(getActivity(), registryResponse -> {
            if (registryResponse != null) {
                Registry detail = registryResponse.getRegistries().get(0);
                title.setText(detail.getTitle());
                Picasso.get().load(getContext().getResources().getString(R.string.urlImage)+detail.getImage()).into(imageView);
                body.setText(StringEscapeUtils.unescapeHtml4(detail.getDescription()));
                progressBar.setVisibility(View.GONE);

            }
        });
    }


}
