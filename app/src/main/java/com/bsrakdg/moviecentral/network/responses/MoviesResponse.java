package com.bsrakdg.moviecentral.network.responses;

import com.bsrakdg.moviecentral.models.Movie;

import java.util.List;

public class MoviesResponse {
    private int page;
    private int total_results;
    private int total_pages;
    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }
}
