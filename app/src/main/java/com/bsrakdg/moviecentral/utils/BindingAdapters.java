package com.bsrakdg.moviecentral.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class BindingAdapters {

    @BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if (adapter != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter("setImageApi")
    public static void setImageApi(ImageView imageView, String url) {
        StringBuilder stringBuilder = new StringBuilder("https://image.tmdb.org/t/p/w500/");
        stringBuilder.append(url);

        if (url != null) {
            Glide.with(imageView.getContext())
                    .load(stringBuilder.toString())
                    .fitCenter()
                    .into(imageView);
        }
    }
}
