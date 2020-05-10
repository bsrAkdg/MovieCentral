package com.bsrakdg.moviecentral.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsrakdg.moviecentral.adapters.viewholders.GenreViewHolder;
import com.bsrakdg.moviecentral.models.Genre;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenreViewHolder> {

    private List<Genre> genres;

    public GenresAdapter() {
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenreViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.onBind(genres.get(position));
    }

    @Override
    public int getItemCount() {
        return genres != null ? genres.size() : 0;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
        notifyDataSetChanged();
    }

}
