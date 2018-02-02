package com.infinite.todoapp.taskDetail;

import com.infinite.todoapp.data.Task;
import com.infinite.todoapp.data.source.TaskDataRepository;

/**
 * Created by 19082 on 2017/6/8.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private TaskDetailContract.View mView;
    private TaskDataRepository mTaskDataRepository;
    private String mTaskId;
    private Task mTask;

    public TaskDetailPresenter(String taskId, TaskDetailContract.View view, TaskDataRepository repository) {
        mView = view;
        mTaskDataRepository = repository;
        mTaskId = taskId;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }



    @Override
    public void deleteTask() {
        mTaskDataRepository.deleteTask(mTaskId);
    }

    @Override
    public void completeTask() {
        mTaskDataRepository.completeTask(mTaskId);
    }

    @Override
    public void activiteTask() {
        mTaskDataRepository.activiteTask(mTaskId);
    }


    private void loadTask(){

    }
}
