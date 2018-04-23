package com.example.jjsampayo.mvpsample1.modules.main.watch_god_list;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jjsampayo.mvpsample1.R;
import com.example.jjsampayo.mvpsample1.modules.main.MainActivity;
import com.example.jjsampayo.mvpsample1.modules.main.adapters.GodsAdapter;
import com.example.jjsampayo.mvpsample1.beans.God;
import com.example.jjsampayo.mvpsample1.modules.main.edit_add_god.EditAddGodFragment;

import java.util.ArrayList;

/**
 * Created by
 *      jjsampayo on 24/03/2018.
 */

public class MainFragment extends Fragment implements MainFragmentContracts.MainFragmentView {

    private View mainView;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView textViewPlaceholder;
    private FloatingActionButton floatingActionButton;

    private GodsAdapter godsAdapter;

    private MainFragmentPresenter presenter;

    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new MainFragmentPresenter(this);
        getLifecycle().addObserver(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
                            , @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_show_god, container, false);

        handleViews();

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return mainView;
    }

    @Override
    public void onStop() {
        super.onStop();
        getLifecycle().removeObserver(presenter);
    }

    @Override
    public void handleViews() {
        recyclerView        = mainView.findViewById(R.id.fra_main_recycler);
        progressBar         = mainView.findViewById(R.id.fra_main_progress);
        textViewPlaceholder = mainView.findViewById(R.id.fra_main_tv_placeholder);

        if (getActivity() != null)
            floatingActionButton = getActivity().findViewById(R.id.act_main_float);

        handleListeners();
    }

    @Override
    public void handleListeners() {

    }

    @Override
    public void showPlaceholder(String text) {
        textViewPlaceholder.setText(text);
        textViewPlaceholder.setVisibility(View.VISIBLE);

    }

    @Override
    public void hidePlaceholder() {
        textViewPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showFloatButton() {
        floatingActionButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFloatButton() {
        floatingActionButton.setVisibility(View.GONE);
    }

    @Override
    public void openDetailFragment(God god) {
        if (getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction()
                         .replace(R.id.act_main_content_main, EditAddGodFragment.newInstance(god))
                         .addToBackStack(MainActivity.STACK_TAG)
                         .commit();
    }

    @Override
    public void showConfirmationDialog(final God god) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                                    .setTitle(R.string.dialog_delete_title)
                                    .setMessage(R.string.dialog_delete_message)
                                    .setPositiveButton(R.string.dialog_delete_yes, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            presenter.onDeleteConfirmation(god);
                                        }
                                    })
                                    .setNegativeButton(R.string.dialog_delete_no, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

        builder.create().show();
    }

    @Override
    public void inflateData(ArrayList<God> dataList) {
        godsAdapter = new GodsAdapter(presenter, dataList);
        recyclerView.setAdapter(godsAdapter);
    }

    @Override
    public void reloadData(ArrayList<God> data) {
        godsAdapter.notifyDataSetChanged();
    }
}
