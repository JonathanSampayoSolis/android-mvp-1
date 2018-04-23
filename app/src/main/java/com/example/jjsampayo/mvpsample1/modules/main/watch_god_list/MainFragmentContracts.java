package com.example.jjsampayo.mvpsample1.modules.main.watch_god_list;

import com.example.jjsampayo.mvpsample1.beans.God;
import com.example.jjsampayo.mvpsample1.base_contracts.BaseContracts;

import java.util.ArrayList;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public interface MainFragmentContracts {

    interface MainFragmentModel {

        void loadData(MainFragmentCallbacks callbacks);

        void deleteGod(God god, MainFragmentCallbacks callbacks);
    }

    interface MainFragmentView extends BaseContracts.BaseView {
        void showPlaceholder(String text);
        void hidePlaceholder();

        void showProgress();
        void hideProgress();

        void showFloatButton();
        void hideFloatButton();

        void openDetailFragment(God god);

        void showConfirmationDialog(God god);

        void reloadData(ArrayList<God> data);

        void inflateData(ArrayList<God> dataList);
    }

    interface MainFragmentPresenter extends BaseContracts.BasePresenter, MainFragmentCallbacks {
        void onCardClick(God god);
        void onCardLongClick(God god);

        void onDeleteConfirmation(God god);
    }

    interface MainFragmentCallbacks {
        void onLoadSuccessful(ArrayList<God> data);
        void onLoadFailed(String error);

        void onDeleteSuccessful(ArrayList<God> data);
        void onDeleteError(int error);
    }

}
