package com.bsrakdg.moviecentral.ui;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bsrakdg.moviecentral.R;
import com.bsrakdg.moviecentral.databinding.FragmentGenresBinding;
import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.utils.Resource;
import com.bsrakdg.moviecentral.utils.listeners.OnItemClickListener;
import com.bsrakdg.moviecentral.viewmodels.GenresViewModel;

public class GenresFragment extends BaseFragment implements OnItemClickListener<Genre> {

    private static final String TAG = "GenresFragment";

    private GenresViewModel genresViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_genres;
    }

    @Override
    protected void onBindDataBinding(ViewDataBinding binding) {
        genresViewModel = new ViewModelProvider(this).get(GenresViewModel.class);
        ((FragmentGenresBinding) binding).setGenreViewModel(genresViewModel);

        genresViewModel.getAdapter().setOnItemClickListener(this);

        subscribeViewModel();
    }

    @Override
    public void onItemClick(Genre item) {
        if (getView() != null) {
            Navigation.findNavController(getView())
                    .navigate(GenresFragmentDirections.actionGenresFragmentToMoviesFragment(item));
        }
    }

    private void subscribeViewModel() {
        // for memory leak
        genresViewModel.getGenres().removeObservers(getViewLifecycleOwner());
        genresViewModel.getGenres()
                .observe(getViewLifecycleOwner(), listResource -> {
                    if (listResource != null) {
                        if (listResource.getStatus() == Resource.Status.LOADING) {
                            // show progress

                        } else if (listResource.getStatus() == Resource.Status.SUCCESS) {
                            // update adapter
                            genresViewModel.getAdapter().setItems(listResource.getBody());

                        } else if (listResource.getStatus() == Resource.Status.ERROR) {
                            // show error

                        }
                    }
                });
    }
}
