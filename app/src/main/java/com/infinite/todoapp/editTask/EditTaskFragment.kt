package com.infinite.todoapp.editTask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.infinite.todoapp.R

/**
 * Created by xz on 2018/2/7.
 */
class EditTaskFragment : Fragment(), EditTaskContract.View {


    private lateinit var mPresenter: EditTaskContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_edit_task,container,false)
        return view
    }

    override fun setPresenter(presenter: EditTaskContract.Presenter) {
        mPresenter = presenter
    }

    override fun updateTask(title: String, description: String) {
        mPresenter.updateTask(title, description)
    }

    override fun showTaskTitle(title: String) {

    }

    override fun showTaskDescription(description: String) {
    }
}