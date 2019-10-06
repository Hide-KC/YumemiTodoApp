package work.kcs_labo.yumemitodoapp.data.source.local

import androidx.room.*
import work.kcs_labo.yumemitodoapp.data.Task

@Dao interface TasksDao {
  @Query("SELECT * FROM tasks")
  fun findAll(): List<Task>

  @Query("SELECT * FROM tasks WHERE name LIKE :taskName")
  fun find(taskName: String): List<Task>

  @Query("SELECT * FROM tasks WHERE id = :id")
  fun find(id: Long): Task

  @Query("SELECT * FROM tasks WHERE isCompleted = 'true'")
  fun findCompleted(): List<Task>

  @Query("SELECT * FROM tasks WHERE isCompleted = 'false'")
  fun findActive(): List<Task>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(task: Task)

  @Update
  fun update(task: Task): Int

  @Query("DELETE FROM tasks")
  fun deleteAll(): Int

  @Delete
  fun delete(task: Task): Int
}