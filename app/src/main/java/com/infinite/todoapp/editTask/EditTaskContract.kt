package com.infinite.todoapp.editTask

import com.infinite.todoapp.IBasePresenter
import com.infinite.todoapp.IBaseView

/**
 * Created by xz on 2018/2/7.
 */
class EditTaskContract{

     interface Presenter :IBasePresenter{
         fun updateTask(title:String,description:String)
         fun loadTask()
     }
    interface View :IBaseView<Presenter>{
        fun updateTask(title:String,description:String)
        fun showTaskTitle(title:String)
        fun showTaskDescription(description: String)


    }
}