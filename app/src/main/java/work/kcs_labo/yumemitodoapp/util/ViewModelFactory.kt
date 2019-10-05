package work.kcs_labo.yumemitodoapp.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import work.kcs_labo.yumemitodoapp.MainActViewModel
import work.kcs_labo.yumemitodoapp.data.Injection
import work.kcs_labo.yumemitodoapp.data.source.TasksRepository
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(
  private val app: Application,
  private val tasksRepository: TasksRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>) =
    with(modelClass) {
      when {
        isAssignableFrom(MainActViewModel::class.java) ->
          MainActViewModel(app, tasksRepository)
        else ->
          throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
      }
    } as T

  companion object {
    private var INSTANCE : ViewModelFactory? = null

    fun getInstance(app: Application): ViewModelFactory =
      INSTANCE ?: synchronized(ViewModelFactory::class.java) {
        INSTANCE ?: ViewModelFactory(app, Injection.provideTasksRepository(app.applicationContext))
          .also { INSTANCE = it }
      }

    fun destroyInstance() {
      INSTANCE = null
    }
  }
}