package com.example.jjsampayo.mvpsample1.modules.main.edit_add_god;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.jjsampayo.mvpsample1.beans.God;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public class EditAddGodFragmentPresenter implements EditAddGodFragmentContracts.EditAddGodPresener {

    private final String TAG = EditAddGodFragment.class.getName().toUpperCase();

    private EditAddGodFragmentContracts.EditAddGodModel model;
    private EditAddGodFragmentContracts.EditAddGodView view;

    private Context context;

    EditAddGodFragmentPresenter(Fragment view) {
        this.view = (EditAddGodFragmentContracts.EditAddGodView) view;
        this.model = new EditAddGodFragmentModel();

        this.context = view.getContext();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        model.loadSpinnersData(this);
        view.populateData();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        onUnregister();
    }

    @Override
    public void onUnregister() {
        view = null;
    }

    @Override
    public void onClickAddButton(String name, String phanteon, String role, String type) {
        model.saveGod(new God(name, phanteon, type, role), this);
    }

    @Override
    public void onClickEditButton(God oldGod, String name, String pantheon, String role, String type) {
        model.editGod(oldGod, new God(name, pantheon, type, role), this);
    }

    @Override
    public void onLoadSpinnersDataSuccessful(List<ArrayList<String>> data) {
        view.fillSpinners(data.get(0), data.get(1));
    }

    @Override
    public void onLoadSpinnersDataFailed(String error) {
        view.showError(error);
    }

    @Override
    public void onEditGodSuccessful() {
        view.openMainFragment();
    }

    @Override
    public void onEditGodFailed(int error) {
        view.showError(context.getString(error));
    }

    @Override
    public void onLoadGodSuccessful(God data) {
        view.populateData();
    }

    @Override
    public void onLoadGodFailed(int error) {
        view.showError(context.getString(error));
    }

    @Override
    public void onSaveGodSuccessful() {
        view.openMainFragment();
    }

    @Override
    public void onSaveGodFailed(String error) {
        view.showError(error);
    }
}
