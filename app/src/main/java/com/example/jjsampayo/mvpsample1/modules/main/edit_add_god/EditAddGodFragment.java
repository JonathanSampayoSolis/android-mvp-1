package com.example.jjsampayo.mvpsample1.modules.main.edit_add_god;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jjsampayo.mvpsample1.R;
import com.example.jjsampayo.mvpsample1.beans.God;
import com.example.jjsampayo.mvpsample1.modules.main.watch_god_list.MainFragment;

import java.util.List;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public class EditAddGodFragment extends Fragment implements EditAddGodFragmentContracts.EditAddGodView {

    private View mainView;

    private EditText editTextName, editTextPhanteon;
    private Spinner spinnerRole, spinnerType;
    private Button buttonSave;
    private View mainLayout;

    private EditAddGodFragmentPresenter presenter;

    public static final String EXTRA_GOD = "EXTRA_GOD";
    private static boolean IS_EDIT_VIEW = false;

    public static EditAddGodFragment newInstance() {

        Bundle args = new Bundle();

        EditAddGodFragment fragment = new EditAddGodFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static EditAddGodFragment newInstance(God god) {

        Bundle args = new Bundle();

        args.putParcelable(EXTRA_GOD, god);
        EditAddGodFragment fragment = new EditAddGodFragment();
        fragment.setArguments(args);

        IS_EDIT_VIEW = true;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_add_god, container, false);

        handleViews();

        return mainView;
    }

    @Override
    public void onStart() {
        super.onStart();

        presenter = new EditAddGodFragmentPresenter(this);
        getLifecycle().addObserver(presenter);
    }

    @Override
    public void onStop() {
        super.onStop();

        getLifecycle().removeObserver(presenter);
    }

    @Override
    public void handleViews() {
        editTextName     = mainView.findViewById(R.id.fra_add_name);
        editTextPhanteon = mainView.findViewById(R.id.fra_add_phaneton);

        spinnerRole = mainView.findViewById(R.id.fra_add_role);
        spinnerType = mainView.findViewById(R.id.fra_add_type);

        buttonSave = mainView.findViewById(R.id.fra_add_btn_save);

        mainLayout = mainView.findViewById(R.id.fra_add_lay_main);

        handleListeners();
    }

    @Override
    public void handleListeners() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name     = editTextName.getText().toString();
                String phanteon = editTextPhanteon.getText().toString();
                String role     = spinnerRole.getSelectedItem().toString();
                String type     = spinnerType.getSelectedItem().toString();
                if (IS_EDIT_VIEW && getArguments() != null)
                    presenter.onClickEditButton((God) getArguments().getParcelable(EXTRA_GOD)
                            , name
                            , phanteon
                            , role
                            , type);
                else
                    presenter.onClickAddButton(name, phanteon, role, type);
            }
        });
    }

    @Override
    public void openMainFragment() {
        if (getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.act_main_content_main, new MainFragment())
                    .commit();
    }

    @Override
    public void showError(String error) {
        Snackbar.make(mainLayout, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void fillSpinners(List<String> spinner1, List<String> spinner2) {

        if (getContext() != null) {
            ArrayAdapter<String> adapterTypes = new ArrayAdapter<>(getContext()
                    , android.R.layout.simple_spinner_item
                    , spinner1);

            ArrayAdapter<String> adapterRoles = new ArrayAdapter<>(getContext()
                    , android.R.layout.simple_spinner_item
                    , spinner2);

            adapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapterRoles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerType.setAdapter(adapterTypes);
            spinnerRole.setAdapter(adapterRoles);
        }
    }

    @Override
    public void populateData() {
        God god = getArguments().getParcelable(EXTRA_GOD);
        if (god != null) {
            editTextName.setText(god.getName());
            editTextPhanteon.setText(god.getPhanteon());

            spinnerType.setSelection(getSpinnerIndex(spinnerType, god.getType()));
            spinnerRole.setSelection(getSpinnerIndex(spinnerRole, god.getRole()));
        }
    }

    private int getSpinnerIndex(Spinner spinner, String string){
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(string))
                index = i;
            Log.d(":p", spinner.getItemAtPosition(i).toString());
        }

        return index;
    }

}
