package com.infinite.todoapp.addTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.Window;

import com.infinite.todoapp.R;
import com.infinite.todoapp.data.source.TaskDataRepository;
import com.infinite.todoapp.data.source.local.TaskLocalSource;
import com.infinite.todoapp.data.source.remote.TaskRemoteSource;
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
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
        mTaskId = getIntent().getStringExtra(AddTaskFragment.ARGUMENT_EDIT_TASK_ID);
        setToolBarTitle(mTaskId);
        AddTaskFragment fragment = AddTaskFragment.newInstance();
        fragment.setPresenter(new AddTaskPresenter(null, TaskDataRepository.getInstance(TaskRemoteSource.getInstance(), TaskLocalSource.getInstance(this)), fragment));
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setToolBarTitle(String taskId) {
        if (TextUtils.isEmpty(taskId)) {
            mActionBar.setTitle(getResources().getString(R.string.add_task));
        } else {
            mActionBar.setTitle(getResources().getString(R.string.edit_task));
        }
    }
}
