package com.example.jjsampayo.mvpsample1.modules.main.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jjsampayo.mvpsample1.beans.God;
import com.example.jjsampayo.mvpsample1.databinding.ItemActMainBinding;
import com.example.jjsampayo.mvpsample1.modules.main.watch_god_list.MainFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public class GodsAdapter extends RecyclerView.Adapter<GodsAdapter.GodsViewHolder> {

    private ArrayList<God> data;
    private MainFragmentPresenter presenter;

    public GodsAdapter(MainFragmentPresenter presenter, ArrayList<God> data) {
        this.data = data;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public GodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemActMainBinding binding = ItemActMainBinding.inflate(LayoutInflater.from(parent.getContext())
                                , parent, false);

        return new GodsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull GodsViewHolder holder, int position) {
        holder.binding.setGodModel(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class GodsViewHolder extends RecyclerView.ViewHolder {
        ItemActMainBinding binding;

        GodsViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.onCardClick(data.get(getAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    presenter.onCardLongClick(data.get(getAdapterPosition()));
                    return true;
                }
            });
        }
    }

}
