package com.bsrakdg.moviecentral.ui;

import android.util.Log;

import androidx.databinding.ViewDataBinding;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.databinding.FragmentMoviesBinding;

public class MoviesFragment extends BaseFragment {

    private static final String TAG = "MoviesFragment";

    private FragmentMoviesBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movies;
    }

    @Override
    protected void onBindDataBinding(ViewDataBinding binding) {
        this.binding = (FragmentMoviesBinding) binding;

        if (getArguments() != null) {
            Log.d(TAG, "onBindDataBinding: " + MoviesFragmentArgs.fromBundle(getArguments()).getGenre());
        }
    }
}
