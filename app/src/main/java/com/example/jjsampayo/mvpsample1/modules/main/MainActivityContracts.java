package com.example.jjsampayo.mvpsample1.modules.main;

import com.example.jjsampayo.mvpsample1.base_contracts.BaseContracts;

/**
 * Created by
 *      jjsampayo on 23/03/2018.
 */

public interface MainActivityContracts {

    interface MainView extends BaseContracts.BaseView {
        void openMainFragment();

        void openAddFragment();
    }

    interface MainPresenter extends BaseContracts.BasePresenter {
        void onFloatClick();
    }
}
