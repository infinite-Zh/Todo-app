package com.infinite.todoapp.editTask

import com.infinite.todoapp.data.Task
import com.infinite.todoapp.data.source.TaskDataRepository
import com.infinite.todoapp.data.source.TaskDataSource

/**
 * Created by xz on 2018/2/14.
 */
class EditTaskPresenter(taskDataRepository: TaskDataRepository) : EditTaskContract.Presenter {

    val taskDataRepo: TaskDataRepository = taskDataRepository
    var mTask: Task? = null

    override fun start() {
    }

    override fun updateTask(title: String, description: String) {
        taskDataRepo.updateTask(Task(title, description, mTask!!.id))
    }

    override fun loadTask(taskId: String) {
        taskDataRepo.getTask(taskId, object : TaskDataSource.GetTaskCallback {
            override fun onTaskLoaded(task: Task?) {
                mTask = task
            }

            override fun onDataNotAvailable() {

            }
        })
    }
}