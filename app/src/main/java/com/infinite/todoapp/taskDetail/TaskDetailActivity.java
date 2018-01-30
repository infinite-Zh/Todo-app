package com.infinite.todoapp.taskDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.infinite.todoapp.R;

/**
 * Created by 19082 on 2017/6/8.
 */

public class TaskDetailActivity extends AppCompatActivity implements TaskDetailContract.View {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void setPresenter(TaskDetailContract.Presenter presenter) {

    }

    @Override
    public void showTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void showDescription(String description) {

    }
}
