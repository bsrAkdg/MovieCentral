package com.bsrakdg.moviecentral.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.databinding.FragmentGenresBinding;
import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.viewmodels.GenresViewModel;

import java.util.List;

public class GenresFragment extends BaseFragment {

    private static final String TAG = "GenresFragment";

    private GenresViewModel genresViewModel;
    private FragmentGenresBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_genres;
    }

    @Override
    protected void onBindDataBinding(ViewDataBinding binding) {
        this.binding = (FragmentGenresBinding) binding;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");

        genresViewModel = new ViewModelProvider(this).get(GenresViewModel.class);

        binding.setGenreViewModel(genresViewModel);

        subscribeViewModel();

        genresViewModel.queryGenres();
    }

    private void subscribeViewModel() {
        // for memory leak
        genresViewModel.getGenres().removeObservers(getViewLifecycleOwner());
        genresViewModel.getGenres().observe(getViewLifecycleOwner(), new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                if (genres != null) {
                    genresViewModel.getAdapter().setItems(genres);
                }
            }
        });
    }
}
