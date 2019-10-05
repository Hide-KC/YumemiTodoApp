package work.kcs_labo.yumemitodoapp.data.source

import work.kcs_labo.yumemitodoapp.data.Task

interface TasksDataSource {
  fun find(taskName: String): List<Task>
  fun findCompleted(): List<Task>
  fun findActive(): List<Task>
  fun insert(task: Task)
  fun update(task: Task)
  fun delete(task: Task)
  fun deleteAll()
}