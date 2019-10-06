package work.kcs_labo.yumemitodoapp.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import work.kcs_labo.yumemitodoapp.databinding.TaskItemBinding

class TasksAdapter(context: Context, private val layoutId: Int, val taskModels: MutableList<TaskModel>) : ArrayAdapter<TaskModel>(context, layoutId, taskModels) {

  private val inflater = LayoutInflater.from(context)
  private lateinit var binding: TaskItemBinding

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    when (convertView) {
      null -> {
        binding = DataBindingUtil.inflate(inflater, layoutId, parent, false)
        binding.root.tag = binding
      }
      else -> binding = convertView.tag as TaskItemBinding
    }

    this.getItem(position)
      .let {
        if (it != null) binding.taskmodel = it
      }

    setupListener(position)

    return binding.root
  }

  private fun setupListener(position: Int) {
    val taskModel = taskModels[position]
    binding.root.setOnClickListener {
      taskModel.taskClick(taskModel.task)
    }
    binding.imageView.setOnClickListener {
      taskModel.deleteClick(taskModel.task)
    }
  }
}