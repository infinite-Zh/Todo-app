package com.infinite.todoapp.addTask;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.infinite.todoapp.R;

/**
 * Created by 19082 on 2017/6/7.
 */

public class AddTaskFragment extends Fragment implements AddTaskContract.View{

    public static final String ARGUMENT_EDIT_TASK_ID="edit_task_id";
    private AddTaskContract.Presenter mPresenter;
    private EditText mTitleEd,mDesEt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addtask_frag, container, false);
        mTitleEd= (EditText) root.findViewById(R.id.add_task_title);
        mDesEt= (EditText) root.findViewById(R.id.add_task_description);
        FloatingActionButton fab= (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_task_done);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.saveTask(mTitleEd.getText().toString(),mDesEt.getText().toString());
            }
        });
        return root;
    }

    public static AddTaskFragment newInstance(){
        return new AddTaskFragment();
    }

    @Override
    public void setPresenter(AddTaskContract.Presenter presenter) {
        this.mPresenter=presenter;
    }

    @Override
    public void showTaskList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showEmptyTaskError() {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setDescription(String description) {

    }


}
