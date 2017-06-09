package com.infinite.todoapp.addTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.infinite.todoapp.R;
import com.infinite.todoapp.util.ActivityUtils;

/**
 * Created by 19082 on 2017/6/7.
 */

public class AddTaskActivity extends AppCompatActivity {

    private String mTaskId;
    private Toolbar mToolBar;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.addtask_act);
        mToolBar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mActionBar=getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        AddTaskFragment fragment = AddTaskFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
