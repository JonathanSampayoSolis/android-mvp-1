package com.example.jjsampayo.mvpsample1.modules.main;

import com.example.jjsampayo.mvpsample1.modules.main.MainActivityContracts.MainView;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jjsampayo.mvpsample1.R;
import com.example.jjsampayo.mvpsample1.modules.main.edit_add_god.EditAddGodFragment;
import com.example.jjsampayo.mvpsample1.modules.main.watch_god_list.MainFragment;

public class MainActivity extends AppCompatActivity implements MainView {
    public static final String STACK_TAG = "modules.main";

    private MainActivityPresenter presenter;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleViews();

        presenter = new MainActivityPresenter(this);
        getLifecycle().addObserver(presenter);
    }

    @Override
    public void handleViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.act_main_float);

        handleListeners();
    }

    @Override
    public void handleListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onFloatClick();
            }
        });
    }

    @Override
    public void openMainFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.act_main_content_main, MainFragment.newInstance())
                .commit();
    }

    @Override
    public void openAddFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.act_main_content_main, EditAddGodFragment.newInstance())
                .addToBackStack(STACK_TAG)
                .commit();
    }
}
