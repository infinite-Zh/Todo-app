package com.infinite.todoapp.taskDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.infinite.todoapp.R;
import com.infinite.todoapp.data.source.TaskDataRepository;
import com.infinite.todoapp.data.source.local.TaskLocalSource;
import com.infinite.todoapp.data.source.remote.TaskRemoteSource;
import com.infinite.todoapp.util.ActivityUtils;


/**
 * Created by 19082 on 2017/6/8.
 */

public class TaskDetailActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);
        processIntent();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("todo-task-detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TaskDetailFragment fragment = TaskDetailFragment.getInstance(mTaskId);
        fragment.setPresenter(new TaskDetailPresenter(
                mTaskId,
                fragment,
                TaskDataRepository.getInstance(
                        TaskRemoteSource.getInstance(),
                        TaskLocalSource.getInstance(this))));
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
    }

    private String mTaskId;

    private void processIntent() {
        Intent intent = getIntent();
        mTaskId = intent.getStringExtra("taskId");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
