package com.bsrakdg.moviecentral.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bsrakdg.moviecentral.adapters.viewholders.BaseViewHolder;
import com.bsrakdg.moviecentral.utils.listeners.OnItemClickListener;

import java.util.List;

public abstract class BaseAdapter<T, V extends BaseViewHolder<T>> extends RecyclerView.Adapter<V> implements OnItemClickListener<T> {

    OnItemClickListener<T> listener;
    private List<T> items;

    BaseAdapter() {
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater,
                getItemLayoutId(viewType), parent, false);
        return getViewHolder(binding, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        if (items != null && !items.isEmpty()) {
            return items.size();
        }
        return 0;
    }

    @Override
    public void onItemClick(T item) {
        if (listener != null) {
            listener.onItemClick(item);
        }
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.listener = onItemClickListener;
    }

    protected abstract int getItemLayoutId(int viewType);

    protected abstract V getViewHolder(ViewDataBinding binding, int viewType);
}
