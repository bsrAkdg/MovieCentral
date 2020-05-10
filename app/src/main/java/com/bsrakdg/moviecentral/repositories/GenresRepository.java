package com.bsrakdg.moviecentral.repositories;

public class GenresRepository {

    private static final String TAG = "GenresRepository";

    private static GenresRepository genresRepository;

    private GenresRepository() {
    }

    public static GenresRepository getGenresRepositoryInstance() {
        if (genresRepository == null) {
            genresRepository = new GenresRepository();
        }
        return genresRepository;
    }
}
