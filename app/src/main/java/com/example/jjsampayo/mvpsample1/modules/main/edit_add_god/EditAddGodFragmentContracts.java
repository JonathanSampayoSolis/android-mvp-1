package com.example.jjsampayo.mvpsample1.modules.main.edit_add_god;

import com.example.jjsampayo.mvpsample1.beans.God;
import com.example.jjsampayo.mvpsample1.base_contracts.BaseContracts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public interface EditAddGodFragmentContracts {

    interface EditAddGodModel {
        void saveGod(God newGod, EditAddGodCallbacks callbacks);

        void loadSpinnersData(EditAddGodCallbacks callbacks);

        void editGod(God oldGod, God god, EditAddGodCallbacks callbacks);
    }

    interface EditAddGodView extends BaseContracts.BaseView {
        void fillSpinners(List<String> spinner1, List<String> spinner2);

        void showError(String error);
        void openMainFragment();

        void populateData();
    }

    interface EditAddGodPresener extends BaseContracts.BasePresenter, EditAddGodCallbacks {
        void onClickAddButton(String name, String phanteon, String role, String type);

        void onClickEditButton(God oldGod, String name, String pantheon, String type, String role);
    }

    interface EditAddGodCallbacks {
        void onSaveGodSuccessful();
        void onSaveGodFailed(String error);

        void onLoadSpinnersDataSuccessful(List<ArrayList<String>> data);
        void onLoadSpinnersDataFailed(String error);

        void onEditGodSuccessful();
        void onEditGodFailed(int error);

        void onLoadGodSuccessful(God data);
        void onLoadGodFailed(int error);
    }
}
