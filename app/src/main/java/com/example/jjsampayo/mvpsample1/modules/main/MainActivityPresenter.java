package com.example.jjsampayo.mvpsample1.modules.main;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.jjsampayo.mvpsample1.modules.main.MainActivityContracts.MainPresenter;
import com.example.jjsampayo.mvpsample1.modules.main.MainActivityContracts.MainView;

/**
 * Created by
 *      jjsampayo on 23/03/2018.
 */

public class MainActivityPresenter implements MainPresenter {
    private MainView view;

    MainActivityPresenter(MainView view) {
        this.view  = view;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        view.openMainFragment();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        onUnregister();
    }

    @Override
    public void onFloatClick() {
        if (view != null)
            view.openAddFragment();
    }

    @Override
    public void onUnregister() {
        view = null;
    }

}
