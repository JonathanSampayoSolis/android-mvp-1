package com.example.jjsampayo.mvpsample1.base_contracts;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;

/**
 * Created by
 *      jjsampayo on 23/03/2018.
 */

public interface BaseContracts {

    interface BaseView extends LifecycleOwner {
        void handleViews();
        void handleListeners();
    }

    interface BasePresenter extends LifecycleObserver {
        void onUnregister();
    }

}
