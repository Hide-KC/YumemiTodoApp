package work.kcs_labo.yumemitodoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import work.kcs_labo.yumemitodoapp.data.Task
import work.kcs_labo.yumemitodoapp.data.source.TasksRepository
import work.kcs_labo.yumemitodoapp.list.TaskModel

class MainActViewModel(app: Application, private val repository: TasksRepository) :
  AndroidViewModel(app) {

  private var navigator: MainNavigator? = null

  val allBtnEnable = MutableLiveData<Boolean>(true)
  val activeBtnEnable = MutableLiveData<Boolean>(false)
  val completedBtnEnable = MutableLiveData<Boolean>(false)

  val taskModelsLiveData = MutableLiveData<List<TaskModel>>()

  init {
    loadTasks()
  }

  fun setBtnSelected(btnEnum: BtnEnum) {
    when (btnEnum) {
      BtnEnum.ALL -> {
        allBtnEnable.value = true
        activeBtnEnable.value = false
        completedBtnEnable.value = false
      }
      BtnEnum.ACTIVE -> {
        allBtnEnable.value = false
        activeBtnEnable.value = true
        completedBtnEnable.value = false
      }
      BtnEnum.COMPLETED -> {
        allBtnEnable.value = false
        activeBtnEnable.value = false
        completedBtnEnable.value = true
      }
    }
    loadTasks()
  }

  fun setNavigator(navigator: MainNavigator?) {
    this.navigator = navigator
  }

  private fun loadTasks() {
    viewModelScope.launch(Dispatchers.IO) {
      val list = when {
        allBtnEnable.value == true -> repository.findAll()
        activeBtnEnable.value == true -> repository.findActive()
        completedBtnEnable.value == true -> repository.findCompleted()
        else -> throw IllegalStateException()
      }

      val taskModels = mutableListOf<TaskModel>()
      for (task in list) {
        taskModels.add(
          TaskModel(task).also { it.setNavigator(navigator) }
        )
      }
      taskModels.reverse()

      viewModelScope.launch(Dispatchers.Main) {
        taskModelsLiveData.value = taskModels
      }
    }
  }

  fun addTask(taskName: String) {
    if (taskName.trim().isNotEmpty()) {
      viewModelScope.launch(Dispatchers.IO) {
        val task = Task(0, taskName, false.toString())
        repository.insert(task)
        loadTasks()
      }
    }
  }

  fun deleteTask(id: Long) {
    viewModelScope.launch(Dispatchers.IO) {
      val task = repository.find(id)
      repository.delete(task)
      loadTasks()
    }
  }

  fun deleteCompleted() {
    viewModelScope.launch(Dispatchers.IO) {
      repository.deleteCompleted()
      loadTasks()
    }
  }

  fun updateTask(task: Task) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.update(task)
      loadTasks()
    }
  }

  fun deleteAllTask() {
    viewModelScope.launch(Dispatchers.IO) {
      repository.deleteAll()
      loadTasks()
    }
  }

  enum class BtnEnum {
    ALL, ACTIVE, COMPLETED
  }
}