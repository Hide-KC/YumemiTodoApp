package work.kcs_labo.yumemitodoapp.util


import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import work.kcs_labo.yumemitodoapp.R
import work.kcs_labo.yumemitodoapp.list.TaskModel
import work.kcs_labo.yumemitodoapp.list.TasksAdapter

@BindingAdapter("bind:btnState")
fun Button.setBtnSelected(isSelected: Boolean?) {
  background = if (isSelected != null && isSelected) {
    resources.getDrawable(R.drawable.selected_btn, null)
  } else {
    resources.getDrawable(R.drawable.not_selected_btn, null)
  }
}

@BindingAdapter("bind:taskmodels")
fun ListView.setTaskModels(taskModels: List<TaskModel>?) {
  if (taskModels != null) {
    Log.d("ListViewExt", "taskModels.size = ${taskModels.size}")
    val adapter = this.adapter as TasksAdapter
    adapter.taskModels.let {
      it.clear()
      it.addAll(taskModels)
    }
    adapter.notifyDataSetChanged()
  }
}

@BindingAdapter("bind:isCompleted")
fun CheckBox.setTaskState(isCompleted: String?) {
  if (isCompleted == true.toString()) {
    this.isChecked = true
  }
}

@BindingAdapter("bind:isCompleted")
fun TextView.setTaskState(isCompleted: String?) {
  if (isCompleted == true.toString()) {
    background = resources.getDrawable(R.drawable.state_textview_completed, null)
    text = resources.getText(R.string.completed)
  } else {
    background = resources.getDrawable(R.drawable.state_textview_active, null)
    text = resources.getText(R.string.active)
  }
}