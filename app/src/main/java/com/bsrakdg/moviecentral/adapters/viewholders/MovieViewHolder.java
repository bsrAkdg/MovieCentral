package com.bsrakdg.moviecentral.adapters.viewholders;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.bsrakdg.moviecentral.databinding.ListItemMovieBinding;
import com.bsrakdg.moviecentral.models.Movie;

public class MovieViewHolder extends BaseViewHolder<Movie> {

    private ListItemMovieBinding binding;

    public MovieViewHolder(@NonNull ViewDataBinding binding) {
        super(binding);
        this.binding = (ListItemMovieBinding) binding;
    }

    @Override
    public void onBind(Movie model) {
        super.onBind(model);
        binding.setMovie(model);
    }
}
