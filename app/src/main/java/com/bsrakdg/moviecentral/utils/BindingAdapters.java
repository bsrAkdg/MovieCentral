package com.bsrakdg.moviecentral.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {

    @BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if (adapter != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }
    }
}
