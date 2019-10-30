package com.axelfernandez.telteka.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.axelfernandez.telteka.R;
import com.axelfernandez.telteka.model.Category;
import com.axelfernandez.telteka.model.Registry;
import com.axelfernandez.telteka.ui.detail.DetailFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RegistryAdapter extends RecyclerView.Adapter<RegistryAdapter.RegistryViewHolder>{

    private Context context;
    private List<Registry> registries;

    public RegistryAdapter(Context context, List<Registry> registries){
        this.registries = registries;
        this.context = context;
    }

    @NonNull
    @Override
    public RegistryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.registry_layout,parent,false);
        return new RegistryViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RegistryViewHolder holder, int position) {
        Registry registry = registries.get(position);
        holder.title.setText(registry.getTitle());
        Picasso.get().load(context.getResources().getString(R.string.urlImage)+registry.getImage()).into(holder.imageView);
        holder.cop.setText(registry.getDescription());
        holder.date.setText(registry.getInsertDate());
        holder.getDetail.setOnClickListener(v -> {

            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(activity.getResources().getString(R.string.categortySelected),registry.getIdregistry());
            detailFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).addToBackStack(null).commit();

        });
    }

    @Override
    public int getItemCount() {
        return registries.size();
    }

    public static class RegistryViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView cop;
        TextView date;
        Button getDetail;

        public RegistryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            cop = itemView.findViewById(R.id.item_cop);
            date = itemView.findViewById(R.id.item_date);
            getDetail = itemView.findViewById(R.id.item_getDetail);
        }
    }
}
