package com.bsrakdg.moviecentral.network.responses;

import com.bsrakdg.moviecentral.models.Genre;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenresResponse {
    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }
}
