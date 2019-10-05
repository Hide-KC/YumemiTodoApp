package work.kcs_labo.yumemitodoapp.data

import android.content.Context
import work.kcs_labo.yumemitodoapp.data.source.TasksRepository
import work.kcs_labo.yumemitodoapp.data.source.local.TasksDatabase
import work.kcs_labo.yumemitodoapp.data.source.local.TasksLocalDataSource

object Injection {
  fun provideTasksRepository(context: Context): TasksRepository {
    val database = TasksDatabase.getInstance(context)
    return TasksRepository.getInstance(
      TasksLocalDataSource.getInstance(database.tasksDao())
    )
  }
}