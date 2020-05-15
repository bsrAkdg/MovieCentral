package com.bsrakdg.moviecentral.ui;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.databinding.FragmentMovieDetailBinding;
import com.bsrakdg.moviecentral.viewmodels.MovieDetailViewModel;

public class MovieDetailFragment extends BaseFragment {

    private MovieDetailViewModel movieDetailViewModel;
    private FragmentMovieDetailBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    protected void onBindDataBinding(ViewDataBinding binding) {
        this.binding = (FragmentMovieDetailBinding) binding;
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        if (getArguments() != null) {
            movieDetailViewModel
                    .setMoviesId(MovieDetailFragmentArgs.fromBundle(getArguments()).getMovie()
                            .getId());
        }


    }
}
