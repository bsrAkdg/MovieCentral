package com.bsrakdg.moviecentral.ui;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.databinding.FragmentMoviesBinding;
import com.bsrakdg.moviecentral.models.Movie;
import com.bsrakdg.moviecentral.utils.Resource;
import com.bsrakdg.moviecentral.viewmodels.MoviesViewModel;

import java.util.List;

public class MoviesFragment extends BaseFragment {

    private static final String TAG = "MoviesFragment";

    private MoviesViewModel moviesViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movies;
    }

    @Override
    protected void onBindDataBinding(ViewDataBinding binding) {
        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        if (getArguments() != null) {
            moviesViewModel
                    .setGenresId(MoviesFragmentArgs.fromBundle(getArguments()).getGenre().getId());
        }
        ((FragmentMoviesBinding) binding).setMoviesViewModel(moviesViewModel);

        subscribeViewModel();
    }

    private void subscribeViewModel() {
        moviesViewModel.getMovies().removeObservers(getViewLifecycleOwner());
        moviesViewModel.getMovies()
                .observe(getViewLifecycleOwner(), new Observer<Resource<List<Movie>>>() {
                    @Override
                    public void onChanged(Resource<List<Movie>> listResource) {
                        if (listResource != null) {
                            if (listResource.getStatus() == Resource.Status.LOADING) {
                                // show progress

                            } else if (listResource.getStatus() == Resource.Status.SUCCESS) {
                                // update adapter

                            } else if (listResource.getStatus() == Resource.Status.ERROR) {
                                // show error

                            }
                        }
                    }
                });
    }
}
