package work.kcs_labo.yumemitodoapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.main_act.*
import work.kcs_labo.yumemitodoapp.data.Task
import work.kcs_labo.yumemitodoapp.databinding.MainActBinding
import work.kcs_labo.yumemitodoapp.list.TasksAdapter
import work.kcs_labo.yumemitodoapp.util.obtainViewModel

class MainAct : AppCompatActivity(), MainNavigator {
  override fun onTaskClick(task: Task) {
    Log.d(this.javaClass.simpleName, "onTaskClick")
  }

  override fun onDeleteClick(task: Task) {
    obtainViewModel().deleteTask(task.id)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DataBindingUtil.setContentView<MainActBinding>(this, R.layout.main_act)
      .also {
        val viewModel = obtainViewModel()
        viewModel.setNavigator(this)
        it.viewModel = viewModel
        it.lifecycleOwner = this
      }

    setupWidgets()
  }

  private fun setupWidgets() {
    val viewModel = obtainViewModel()

    add_btn.setOnClickListener {
      viewModel.addTask(new_task.text.toString())
      new_task.editableText.clear()
    }

    all_btn.setOnClickListener {
      viewModel.setBtnSelected(MainActViewModel.BtnEnum.ALL)
    }

    active_btn.setOnClickListener {
      viewModel.setBtnSelected(MainActViewModel.BtnEnum.ACTIVE)
    }

    complete_btn.setOnClickListener {
      viewModel.setBtnSelected(MainActViewModel.BtnEnum.COMPLETED)
    }

    tasks.adapter = TasksAdapter(this, R.layout.task_item, mutableListOf())
  }

  fun obtainViewModel() = obtainViewModel(MainActViewModel::class.java)
}
