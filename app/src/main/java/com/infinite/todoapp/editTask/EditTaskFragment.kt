package com.infinite.todoapp.editTask

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.infinite.todoapp.R
import kotlinx.android.synthetic.main.fragment_edit_task.*

/**
 * Created by xz on 2018/2/7.
 */
class EditTaskFragment : Fragment(), EditTaskContract.View {
    override fun showProgress() {
        rl_progress.visibility=View.VISIBLE
        edit_title.visibility=View.GONE
        edit_description.visibility=View.GONE
    }

    override fun updateComplete() {
        Snackbar.make(view!!, "修改成功", Snackbar.LENGTH_LONG)
        activity.finish()
    }

    companion object {
        fun getInstance(): EditTaskFragment {
            val fragment = EditTaskFragment()
            return fragment
        }
    }


    private lateinit var mPresenter: EditTaskContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_edit_task, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter.start()
    }

    override fun setPresenter(presenter: EditTaskContract.Presenter) {
        mPresenter = presenter
    }

    override fun updateTask() {
        mPresenter.updateTask(edit_title.text.toString(), edit_description.text.toString())
    }

    override fun showTaskTitle(title: String) {
        edit_title.setText(title)
    }

    override fun showTaskDescription(description: String) {
        edit_description.setText(description)
    }
}