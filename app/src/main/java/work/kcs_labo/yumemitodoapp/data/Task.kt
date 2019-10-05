package work.kcs_labo.yumemitodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val name: String,
  val isCompleted: String
)