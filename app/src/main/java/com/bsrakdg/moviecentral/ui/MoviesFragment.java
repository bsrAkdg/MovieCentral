package com.bsrakdg.moviecentral.ui;

import android.util.Log;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.databinding.FragmentMoviesBinding;
import com.bsrakdg.moviecentral.models.Movie;
import com.bsrakdg.moviecentral.utils.Resource;
import com.bsrakdg.moviecentral.utils.listeners.OnItemClickListener;
import com.bsrakdg.moviecentral.viewmodels.MoviesViewModel;

public class MoviesFragment extends BaseFragment implements OnItemClickListener<Movie> {

    private static final String TAG = "MoviesFragment";

    private MoviesViewModel moviesViewModel;
    private FragmentMoviesBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movies;
    }

    @Override
    protected void onBindDataBinding(ViewDataBinding binding) {
        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        moviesViewModel.getMoviesAdapter().setOnItemClickListener(this);

        if (getArguments() != null) {
            moviesViewModel
                    .setGenresId(MoviesFragmentArgs.fromBundle(getArguments()).getGenre().getId());
        }

        this.binding = (FragmentMoviesBinding) binding;
        this.binding.setMoviesViewModel(moviesViewModel);

        subscribeViewModel();
        initSearchView();
    }

    private void initSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchMovies(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void searchMovies(String movies) {
        moviesViewModel.searchMovies(movies);
    }

    @Override
    public void onItemClick(Movie item) {
        if (getView() != null) {
            Navigation.findNavController(getView())
                    .navigate(MoviesFragmentDirections
                            .actionMoviesFragmentToMoviesDetailFragment(item));
        }
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
