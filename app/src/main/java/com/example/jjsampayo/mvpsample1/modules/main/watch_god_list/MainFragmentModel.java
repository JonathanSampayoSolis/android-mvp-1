
package com.example.jjsampayo.mvpsample1.modules.main.watch_god_list;

import android.os.Handler;

import com.example.jjsampayo.mvpsample1.R;
import com.example.jjsampayo.mvpsample1.beans.God;

import com.example.jjsampayo.mvpsample1.repositories.local.MockedDB;

import java.util.ArrayList;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public class MainFragmentModel implements MainFragmentContracts.MainFragmentModel {


    @Override
    public void loadData(final MainFragmentContracts.MainFragmentCallbacks callbacks) {
        if (getData().size() > 0)
            callbacks.onLoadSuccessful(getData());
        else
            callbacks.onLoadFailed("There's no data to print");
    }

    @Override
    public void deleteGod(God god, MainFragmentContracts.MainFragmentCallbacks callbacks) {
        if (MockedDB.GOD_LIST.remove(god))
            callbacks.onDeleteSuccessful(getData());
        else
            callbacks.onDeleteError(R.string.error_removing_god);

    }

    private ArrayList<God> getData() {
        return MockedDB.GOD_LIST;
    }
}
