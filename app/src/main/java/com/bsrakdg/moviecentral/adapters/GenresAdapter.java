package com.bsrakdg.moviecentral.adapters;

import androidx.databinding.ViewDataBinding;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.adapters.viewholders.GenreViewHolder;
import com.bsrakdg.moviecentral.models.Genre;

public class GenresAdapter extends BaseAdapter<Genre, GenreViewHolder> {

    public GenresAdapter() {
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.list_item_genre;
    }

    @Override
    protected GenreViewHolder getViewHolder(ViewDataBinding binding, int viewType) {
        return new GenreViewHolder(binding, listener);
    }
}
