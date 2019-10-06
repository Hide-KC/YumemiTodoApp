package work.kcs_labo.yumemitodoapp.data.source

import work.kcs_labo.yumemitodoapp.data.Task
import work.kcs_labo.yumemitodoapp.data.source.local.TasksLocalDataSource

class TasksRepository(
  private val tasksLocalDataSource: TasksLocalDataSource
): TasksDataSource {
  override fun findAll(): List<Task> {
    return tasksLocalDataSource.findAll()
  }

  override fun find(taskName: String): List<Task> {
    return tasksLocalDataSource.find(taskName)
  }

  override fun find(id: Long): Task {
    return tasksLocalDataSource.find(id)
  }

  override fun findCompleted(): List<Task> {
    return tasksLocalDataSource.findCompleted()
  }

  override fun findActive(): List<Task> {
    return tasksLocalDataSource.findActive()
  }

  override fun insert(task: Task) {
    tasksLocalDataSource.insert(task)
  }

  override fun update(task: Task): Int {
    return tasksLocalDataSource.update(task)
  }

  override fun delete(task: Task): Int {
    return tasksLocalDataSource.delete(task)
  }

  override fun deleteAll(): Int {
    return tasksLocalDataSource.deleteAll()
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