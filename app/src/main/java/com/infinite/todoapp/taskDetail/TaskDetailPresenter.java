package com.infinite.todoapp.taskDetail;

import android.text.TextUtils;

import com.infinite.todoapp.data.Task;
import com.infinite.todoapp.data.source.TaskDataRepository;
import com.infinite.todoapp.data.source.TaskDataSource;

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
        loadTask();
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

    @Override
    public void editTask() {
        mView.editTask(mTaskId);
    }

    @Override
    public void updateTask() {
        loadTask();
    }


    private void loadTask() {
        if (TextUtils.isEmpty(mTaskId)) {
            mView.showMissingTask();
        }
        mTaskDataRepository.getTask(mTaskId, new TaskDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                mView.showTitle(task.getTitle());
                mView.showDescription(task.getDescription());
                mView.showTaskState(task.isCompleted());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
