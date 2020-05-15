package com.bsrakdg.moviecentral.adapters.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bsrakdg.moviecentral.utils.listeners.OnItemClickListener;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    OnItemClickListener<T> listener;
    private T model;

    BaseViewHolder(@NonNull ViewDataBinding binding, OnItemClickListener<T> listener) {
        super(binding.getRoot());
        this.listener = listener;
    }

    BaseViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onItemClick(model);
        }
    }

    public void onBind(T model) {
        this.model = model;
    }
}
