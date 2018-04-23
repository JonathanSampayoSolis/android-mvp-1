package com.example.jjsampayo.mvpsample1.modules.main.edit_add_god;

import com.example.jjsampayo.mvpsample1.R;
import com.example.jjsampayo.mvpsample1.beans.God;
import com.example.jjsampayo.mvpsample1.repositories.local.MockedDB;
import com.example.jjsampayo.mvpsample1.utils.StringValidationUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public class EditAddGodFragmentModel implements EditAddGodFragmentContracts.EditAddGodModel {

    @Override
    public void saveGod(God newGod, EditAddGodFragmentContracts.EditAddGodCallbacks callbacks) {
        if (addNewGod(newGod))
            callbacks.onSaveGodSuccessful();
        else
            callbacks.onSaveGodFailed("The given input has errors.");
    }

    @Override
    public void loadSpinnersData(EditAddGodFragmentContracts.EditAddGodCallbacks callbacks) {
        if (loadSpinnersData().size() > 0)
            callbacks.onLoadSpinnersDataSuccessful(loadSpinnersData());
        else
            callbacks.onLoadSpinnersDataFailed("The spinner info is empty");
    }

    private List<ArrayList<String>> loadSpinnersData() {
        return Arrays.asList(new ArrayList<>(MockedDB.GOD_ROLES)
                , new ArrayList<>(MockedDB.GOD_TYPES));
    }

    private boolean addNewGod(God god) {
        try {
            return !StringValidationUtil.isEmpty(god.getName())
                    && !StringValidationUtil.isEmpty(god.getPhanteon())
                    && !StringValidationUtil.isEmpty(god.getRole())
                    && !StringValidationUtil.isEmpty(god.getType())
                    && MockedDB.GOD_LIST.add(god);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void editGod(God oldGod, God god, EditAddGodFragmentContracts.EditAddGodCallbacks callbacks) {
        try {
            MockedDB.GOD_LIST.set(MockedDB.GOD_LIST.indexOf(oldGod), god);
            callbacks.onEditGodSuccessful();
        } catch (IndexOutOfBoundsException e) {
            callbacks.onEditGodFailed(R.string.error_editing_god);
        }
    }
}
