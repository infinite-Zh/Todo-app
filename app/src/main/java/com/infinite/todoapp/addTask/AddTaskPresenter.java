package com.infinite.todoapp.addTask;

import android.text.TextUtils;
import android.util.Log;

import com.infinite.todoapp.data.Task;
import com.infinite.todoapp.data.source.TaskDataRepository;

/**
 * Created by 19082 on 2017/6/7.
 */

public class AddTaskPresenter implements AddTaskContract.Presenter {

    private AddTaskContract.View view;
    private TaskDataRepository mTaskDataRepository;
    private String mTaskId;
    public AddTaskPresenter(String taskId, TaskDataRepository repository,AddTaskContract.View v){
        view=v;
        this.mTaskId=taskId;
        mTaskDataRepository=repository;
        view.setPresenter(this);
    }

    private boolean isNewTask(){
        return TextUtils.isEmpty(mTaskId);
    }
    @Override
    public void start() {

    }

    @Override
    public void saveTask(String title, String description) {
        Log.e("save",title+":"+description);
        if (isNewTask()){
            creaateTask(title,description);
        }else {

        }
    }

    @Override
    public void populateTask() {

    }

    @Override
    public boolean isDataMissing() {
        return false;
    }

    private void creaateTask(String title,String description){
        Task task=new Task(title,description);
        mTaskDataRepository.saveTask(task);
        view.showTaskList();
    }
}
