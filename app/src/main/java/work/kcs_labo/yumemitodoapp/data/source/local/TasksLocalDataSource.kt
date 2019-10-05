package work.kcs_labo.yumemitodoapp.data.source.local

import work.kcs_labo.yumemitodoapp.data.Task
import work.kcs_labo.yumemitodoapp.data.source.TasksDataSource

class TasksLocalDataSource(private val tasksDao: TasksDao) : TasksDataSource {
  override fun find(taskName: String): List<Task> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun findCompleted(): List<Task> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun findActive(): List<Task> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun insert(task: Task) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun update(task: Task) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun delete(task: Task) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteAll() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  companion object {
    private var INSTANCE: TasksLocalDataSource? = null
    private val lock = Any()

    fun getInstance(tasksDao: TasksDao): TasksLocalDataSource =
      INSTANCE ?: synchronized(lock) {
        INSTANCE ?: TasksLocalDataSource(tasksDao)
          .also {
            INSTANCE = it
          }
      }
  }
}