package com.bsrakdg.moviecentral.adapters.viewholders;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.bsrakdg.moviecentral.databinding.ListItemGenreBinding;
import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.utils.listeners.OnItemClickListener;

public class GenreViewHolder extends BaseViewHolder<Genre> {

    private ListItemGenreBinding binding;

    public GenreViewHolder(@NonNull ViewDataBinding binding,
                           OnItemClickListener<Genre> listener) {
        super(binding, listener);
        this.binding = (ListItemGenreBinding) binding;
        this.binding.getRoot().setOnClickListener(this);
    }

    @Override
    public void onBind(Genre model) {
        super.onBind(model);
        binding.setGenre(model);
    }
}
