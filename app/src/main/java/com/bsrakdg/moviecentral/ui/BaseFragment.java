package com.bsrakdg.moviecentral.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil
                .inflate(inflater, getLayoutId(), container, false);
        onBindDataBinding(binding);
        return binding.getRoot();
    }

    protected abstract int getLayoutId();

    protected abstract void onBindDataBinding(ViewDataBinding binding);
}
