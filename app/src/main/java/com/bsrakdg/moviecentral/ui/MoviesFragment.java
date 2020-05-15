package com.bsrakdg.moviecentral.ui;

import android.util.Log;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.databinding.FragmentMoviesBinding;
import com.bsrakdg.moviecentral.utils.Resource;
import com.bsrakdg.moviecentral.viewmodels.MoviesViewModel;

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
                .observe(getViewLifecycleOwner(), listResource -> {
                    Log.d(TAG, "subscribeViewModel: " + listResource.getStatus());
                    if (listResource.getStatus() == Resource.Status.LOADING) {
                        // show progress

                    } else if (listResource.getStatus() == Resource.Status.SUCCESS) {
                        // update adapter
                        moviesViewModel.getMoviesAdapter().setItems(listResource.getBody());
                    } else if (listResource.getStatus() == Resource.Status.ERROR) {
                        // show error
                        Log.e(TAG, "subscribeViewModel: " + listResource.getMessage());
                    }
                });
    }
}
