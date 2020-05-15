package com.bsrakdg.moviecentral.adapters.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.bsrakdg.moviecentral.databinding.ListItemMovieBinding;
import com.bsrakdg.moviecentral.models.Movie;
import com.bsrakdg.moviecentral.utils.listeners.OnItemClickListener;

public class MovieViewHolder extends BaseViewHolder<Movie> {

    private ListItemMovieBinding binding;

    public MovieViewHolder(@NonNull ViewDataBinding binding, OnItemClickListener<Movie> listener) {
        super(binding, listener);
        this.binding = (ListItemMovieBinding) binding;
    }

    @Override
    public void onBind(Movie model) {
        super.onBind(model);
        binding.setMovie(model);
    }
}
