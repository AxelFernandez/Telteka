package com.axelfernandez.telteka.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.axelfernandez.telteka.R;
import com.axelfernandez.telteka.model.Category;
import com.axelfernandez.telteka.ui.main.MainFragment;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private Context context;
    private List<Category> categories;
    public CategoryAdapter(Context context, List<Category> categories){
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        Log.e("Fuuck", String.valueOf(category.isHadChildren()));
        holder.category.setText(category.getDescription());
        holder.cardView.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            MainFragment registryFragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putString(activity.getResources().getString(R.string.categortySelected),category.getIdcategory());
            registryFragment.setArguments(bundle);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, registryFragment).addToBackStack(null).commit();

        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView category;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv);
            category = (TextView) itemView.findViewById(R.id.cv_categoryName);
        }
    }
}
