package com.bsrakdg.moviecentral.adapters;

import androidx.databinding.ViewDataBinding;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.adapters.viewholders.MovieViewHolder;
import com.bsrakdg.moviecentral.models.Movie;

public class MoviesAdapter extends BaseAdapter<Movie, MovieViewHolder> {

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.list_item_movie;
    }

    @Override
    protected MovieViewHolder getViewHolder(ViewDataBinding binding, int viewType) {
        return new MovieViewHolder(binding);
    }
}
