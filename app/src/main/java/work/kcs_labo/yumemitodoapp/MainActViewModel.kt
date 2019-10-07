package work.kcs_labo.yumemitodoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import work.kcs_labo.yumemitodoapp.data.Task
import work.kcs_labo.yumemitodoapp.data.source.TasksRepository
import work.kcs_labo.yumemitodoapp.list.TaskModel
import java.lang.IllegalStateException

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

  fun setNavigator(navigator: MainNavigator?){
    this.navigator = navigator
  }

  private fun loadTasks() {
    GlobalScope.launch(Dispatchers.IO) {
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

      viewModelScope.launch {
        taskModelsLiveData.value = taskModels
      }
    }
  }

  fun addTask(taskName: String) {
    if(taskName.trim().isNotEmpty()) {
      GlobalScope.launch {
        withContext(Dispatchers.IO) {
          val task = Task(0, taskName, false.toString())
          repository.insert(task)
          loadTasks()
        }
      }
    }
  }

  fun deleteTask(id: Long) {
    GlobalScope.launch {
      withContext(Dispatchers.IO) {
        val task = repository.find(id)
        repository.delete(task)
        loadTasks()
      }
    }
  }

  fun updateTask(task: Task) {
    GlobalScope.launch {
      withContext(Dispatchers.IO) {
        repository.update(task)
        loadTasks()
      }
    }
  }

  fun deleteAllTask() {
    GlobalScope.launch {
      withContext(Dispatchers.IO) {
        repository.deleteAll()
        loadTasks()
      }
    }
  }

  enum class BtnEnum {
    ALL, ACTIVE, COMPLETED
  }
}