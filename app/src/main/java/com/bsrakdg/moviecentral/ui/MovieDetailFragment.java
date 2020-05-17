package com.bsrakdg.moviecentral.ui;

import android.util.Log;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.databinding.FragmentMovieDetailBinding;
import com.bsrakdg.moviecentral.models.Movie;
import com.bsrakdg.moviecentral.utils.Resource;
import com.bsrakdg.moviecentral.viewmodels.MovieDetailViewModel;

public class MovieDetailFragment extends BaseFragment {

    private static final String TAG = "MovieDetailFragment";

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

        subscribeViewModel();
    }

    private void subscribeViewModel() {
        movieDetailViewModel.getMovieDetail().removeObservers(getViewLifecycleOwner());
        movieDetailViewModel.getMovieDetail()
                .observe(getViewLifecycleOwner(), new Observer<Resource<Movie>>() {
                    @Override
                    public void onChanged(Resource<Movie> movieResource) {
                        if (movieResource != null) {
                            switch (movieResource.getStatus()) {
                                case LOADING:
                                    Log.d(TAG, "onChanged: LOADING");
                                    break;
                                case ERROR:
                                    Log.d(TAG, "onChanged: ERROR");
                                    break;
                                case SUCCESS:
                                    Log.d(TAG, "onChanged: SUCCESS");
                                    binding.setMovie(movieResource.getBody());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                });
    }
}
