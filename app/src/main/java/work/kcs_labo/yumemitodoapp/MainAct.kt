package work.kcs_labo.yumemitodoapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.main_act.*
import work.kcs_labo.yumemitodoapp.data.Task
import work.kcs_labo.yumemitodoapp.databinding.MainActBinding
import work.kcs_labo.yumemitodoapp.list.TasksAdapter
import work.kcs_labo.yumemitodoapp.util.obtainViewModel

class MainAct : AppCompatActivity(), MainNavigator {
  override fun onCheckStateChanged(task: Task) {
    val viewModel = obtainViewModel()
    viewModel.updateTask(task)
  }

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

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.toolbar_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.delete_completed -> {
        AlertDialog.Builder(this) // FragmentではActivityを取得して生成
          .setMessage(resources.getString(R.string.delete_completed_msg))
          .setPositiveButton(getString(android.R.string.ok)) { _, _ ->
            obtainViewModel().deleteCompleted()
          }
          .setNegativeButton(getString(android.R.string.cancel)) { _, _ -> }
          .show()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun setupWidgets() {
    val viewModel = obtainViewModel()

    setSupportActionBar(toolbar)

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
