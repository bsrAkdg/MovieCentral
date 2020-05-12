package com.bsrakdg.moviecentral.adapters;

import androidx.databinding.ViewDataBinding;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.adapters.viewholders.GenreViewHolder;
import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.utils.listeners.OnItemClickListener;

public class GenresAdapter extends BaseAdapter<Genre, GenreViewHolder> implements OnItemClickListener<Genre> {

    private OnItemClickListener<Genre> listener;

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

    @Override
    public void onItemClick(Genre item) {
        if (listener != null) {
            listener.onItemClick(item);
        }
    }

    public void setOnItemClickListener(OnItemClickListener<Genre> onItemClickListener) {
        this.listener = onItemClickListener;
    }

}
