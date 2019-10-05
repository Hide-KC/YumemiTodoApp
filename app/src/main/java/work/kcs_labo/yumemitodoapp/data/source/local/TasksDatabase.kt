package work.kcs_labo.yumemitodoapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import work.kcs_labo.yumemitodoapp.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {
  abstract fun tasksDao(): TasksDao

  companion object {
    private var INSTANCE: TasksDatabase? = null
    private val lock = Any()

    fun getInstance(context: Context): TasksDatabase =
      INSTANCE ?: synchronized(lock) {
        INSTANCE ?: Room.databaseBuilder(
          context.applicationContext,
          TasksDatabase::class.java, "Tasks.db"
        )
          .build()
          .also { INSTANCE = it }
      }

    fun destroyInstance() {
      INSTANCE = null
    }
  }
}