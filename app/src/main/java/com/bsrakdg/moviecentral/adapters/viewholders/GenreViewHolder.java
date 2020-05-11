package com.bsrakdg.moviecentral.adapters.viewholders;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.bsrakdg.moviecentral.databinding.ListItemGenreBinding;
import com.bsrakdg.moviecentral.models.Genre;

public class GenreViewHolder extends BaseViewHolder<Genre> {

    private ListItemGenreBinding binding;

    public GenreViewHolder(@NonNull ViewDataBinding binding) {
        super(binding);
        this.binding = (ListItemGenreBinding) binding;
    }

    @Override
    public void onBind(Genre model) {
        super.onBind(model);
        binding.setGenre(model);
    }
}
