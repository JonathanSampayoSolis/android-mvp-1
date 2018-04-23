package com.example.jjsampayo.mvpsample1.modules.main.watch_god_list;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.example.jjsampayo.mvpsample1.beans.God;
import com.example.jjsampayo.mvpsample1.base_contracts.BaseContracts;

import java.util.ArrayList;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public class MainFragmentPresenter implements MainFragmentContracts.MainFragmentPresenter {
    private final String TAG = MainFragmentPresenter.class.getName().toUpperCase();

    private MainFragmentContracts.MainFragmentModel model;
    private MainFragmentContracts.MainFragmentView view;

    public MainFragmentPresenter(MainFragmentContracts.MainFragmentView view) {
        this.view = view;
        this.model = new MainFragmentModel();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d(TAG, "onResume()");
        view.showFloatButton();
        view.showProgress();
        model.loadData(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.d(TAG, "onPause()");
        view.hideFloatButton();
        onUnregister();
    }

    @Override
    public void onUnregister() {
        view = null;
    }


    @Override
    public void onCardClick(God god) {
            view.openDetailFragment(god);
    }

    @Override
    public void onCardLongClick(God god) {
        view.showConfirmationDialog(god);
    }

    @Override
    public void onDeleteConfirmation(God god) {
        model.deleteGod(god, this);
    }

    @Override
    public void onLoadSuccessful(ArrayList<God> data) {
        view.hidePlaceholder();
        view.inflateData(data);
        view.hideProgress();
    }

    @Override
    public void onLoadFailed(String error) {
        view.showPlaceholder(error);
        view.hideProgress();
    }

    @Override
    public void onDeleteSuccessful(ArrayList<God> data) {
        view.reloadData(data);
    }

    @Override
    public void onDeleteError(int error) {

    }
}
