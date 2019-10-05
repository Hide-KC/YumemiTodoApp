package work.kcs_labo.yumemitodoapp.data.source.local

import androidx.room.*
import work.kcs_labo.yumemitodoapp.data.Task

@Dao interface TasksDao {
  @Query("SELECT * FROM tasks")
  fun getAllTasks(): List<Task>

  @Insert
  fun insertAll(vararg tasks: Task)

  @Update
  fun update(task: Task)

  @Delete
  fun delete(task: Task)
}