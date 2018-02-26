package com.infinite.todoapp.taskDetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.infinite.todoapp.R;
import com.infinite.todoapp.editTask.EditTaskActivity;

/**
 * Created by 19082 on 2017/6/8.
 */

public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {

    private TaskDetailContract.Presenter mPresenter;
    private TextView mDetailTitle, mDetailDescription;
    private CheckBox mDetailCompleteStatus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.taskdetail_frag, container, false);
        mDetailTitle = (TextView) root.findViewById(R.id.task_detail_title);
        mDetailDescription = (TextView) root.findViewById(R.id.task_detail_description);
        mDetailCompleteStatus = (CheckBox) root.findViewById(R.id.task_detail_complete);
        mDetailCompleteStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mPresenter.completeTask();
                } else {
                    mPresenter.activiteTask();
                }
            }
        });

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void showTitle(String title) {
        mDetailTitle.setText(title);
    }

    @Override
    public void showDescription(String description) {
        mDetailDescription.setText(description);
    }

    @Override
    public void showMissingTask() {
        mDetailTitle.setText("");
        mDetailDescription.setText(R.string.no_data);
    }

    @Override
    public void editTask(String taskId) {
        Intent intent=new Intent(getActivity(), EditTaskActivity.class);
        intent.putExtra("taskId",taskId);
        startActivityForResult(intent,EDIT_TASK_CODE);
    }

    @Override
    public void setPresenter(TaskDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static TaskDetailFragment getInstance(String taskId) {
        Bundle bundle = new Bundle();
        bundle.putString("taskId", taskId);
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static final int EDIT_TASK_CODE=100;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK&&requestCode==EDIT_TASK_CODE){
            mPresenter.updateTask();
        }
    }
}
