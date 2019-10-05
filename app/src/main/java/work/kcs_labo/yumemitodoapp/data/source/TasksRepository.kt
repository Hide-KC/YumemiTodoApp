package work.kcs_labo.yumemitodoapp.data.source

import work.kcs_labo.yumemitodoapp.data.Task
import work.kcs_labo.yumemitodoapp.data.source.local.TasksLocalDataSource

class TasksRepository(
  val tasksLocalDataSource: TasksLocalDataSource
): TasksDataSource {
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
    private var INSTANCE : TasksRepository? = null
    private val lock = Any()

    fun getInstance(localDataSource: TasksLocalDataSource): TasksRepository =
      INSTANCE ?: synchronized(lock) {
        INSTANCE ?: TasksRepository(localDataSource)
          .also { INSTANCE = it }
      }
  }
}