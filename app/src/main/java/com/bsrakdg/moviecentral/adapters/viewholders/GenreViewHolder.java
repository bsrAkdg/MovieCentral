package com.bsrakdg.moviecentral.adapters.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.bsrakdg.moviecentral.databinding.ListItemGenreBinding;
import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.utils.listeners.OnItemClickListener;

public class GenreViewHolder extends BaseViewHolder<Genre> implements View.OnClickListener {

    private ListItemGenreBinding binding;
    private OnItemClickListener<Genre> listener;
    private Genre model;

    public GenreViewHolder(@NonNull ViewDataBinding binding,
                           OnItemClickListener<Genre> listener) {
        super(binding);
        this.binding.getRoot().setOnClickListener(this);
        this.binding = (ListItemGenreBinding) binding;
        this.listener = listener;
    }

    @Override
    public void onBind(Genre model) {
        super.onBind(model);
        this.model = model;
        binding.setGenre(model);
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onItemClick(model);
        }
    }
}
