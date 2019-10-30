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
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder>{

    private Context context;
    private Registry registries;

    public DetailAdapter(Context context, Registry registries){
        this.registries = registries;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.registry_layout,parent,false);
        return new DetailViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        Registry registry = registries;
        holder.title.setText(registry.getTitle());
        Picasso.get().load(context.getResources().getString(R.string.urlImage)+registry.getImage()).into(holder.imageView);
        holder.cop.setText(registry.getDescription());
        holder.date.setText(registry.getInsertDate());
        holder.getDetail.setOnClickListener(v -> {

           /* AppCompatActivity activity = (AppCompatActivity) v.getContext();
            RegistryFragment registryFragment = new RegistryFragment();
            Bundle bundle = new Bundle();
            bundle.putString(activity.getResources().getString(R.string.categortySelected),category.getIdcategory());
            registryFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, registryFragment).addToBackStack(null).commit(); */

        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class DetailViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView cop;
        TextView date;
        Button getDetail;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_title);
            cop = itemView.findViewById(R.id.item_cop);
            date = itemView.findViewById(R.id.item_date);
            getDetail = itemView.findViewById(R.id.item_getDetail);
        }
    }
}
