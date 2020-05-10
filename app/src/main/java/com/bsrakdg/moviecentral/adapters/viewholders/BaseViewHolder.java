package com.bsrakdg.moviecentral.adapters.viewholders;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    private T model;

    public BaseViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
    }

    public void onBind(T model) {
        this.model = model;
    }
}
