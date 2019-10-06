package work.kcs_labo.yumemitodoapp.data.source.local

import androidx.room.Room
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import work.kcs_labo.yumemitodoapp.data.Task

@RunWith(RobolectricTestRunner::class)
class TasksLocalDataSourceTest {
  lateinit var tasksLocalDataSource: TasksLocalDataSource

  @Before
  fun setUp() {
    val context = androidx.test.InstrumentationRegistry.getTargetContext()
    val db = Room
      .databaseBuilder(context, TasksDatabase::class.java, "DB")
      .allowMainThreadQueries()
      .build()
    tasksLocalDataSource = TasksLocalDataSource(db.tasksDao())
  }

  @After
  fun tearDown() {
  }

  @Test
  fun findAll() {
    var list = tasksLocalDataSource.find("task")
    assertThat(list).isEmpty()

    tasksLocalDataSource.insert(Task(1, "task0", false.toString()))
    tasksLocalDataSource.insert(Task(2, "task1", false.toString()))
    tasksLocalDataSource.insert(Task(3, "task2", false.toString()))
    list = tasksLocalDataSource.findAll()
    assertThat(list).hasSize(3)
  }

  @Test
  fun findByName() {
    var list = tasksLocalDataSource.find("task")
    assertThat(list).isEmpty()

    tasksLocalDataSource.insert(Task(1, "task0", false.toString()))
    tasksLocalDataSource.insert(Task(2, "task1", false.toString()))
    tasksLocalDataSource.insert(Task(3, "task2", false.toString()))

    list = tasksLocalDataSource.find("task1")
    assertThat(list).hasSize(1)
    list = tasksLocalDataSource.find("task")
    assertThat(list).hasSize(3)
  }

  @Test
  fun findById() {
    var list = tasksLocalDataSource.find("task")
    assertThat(list).isEmpty()

    tasksLocalDataSource.insert(Task(1, "task0", false.toString()))
    tasksLocalDataSource.insert(Task(2, "task1", false.toString()))
    tasksLocalDataSource.insert(Task(3, "task2", false.toString()))

    var task = tasksLocalDataSource.find(1)
    assertThat(task.id).isEqualTo(1)
    task = tasksLocalDataSource.find(10)
    assertThat(task).isNull()
  }

  @Test
  fun findCompleted() {
    var list = tasksLocalDataSource.find("task")
    assertThat(list).isEmpty()

    tasksLocalDataSource.insert(Task(1, "task0", false.toString()))
    tasksLocalDataSource.insert(Task(2, "task1", true.toString()))
    tasksLocalDataSource.insert(Task(3, "task2", true.toString()))

    list = tasksLocalDataSource.findCompleted()
    assertThat(list).hasSize(2)
  }

  @Test
  fun findActive() {
    var list = tasksLocalDataSource.find("task")
    assertThat(list).isEmpty()

    tasksLocalDataSource.insert(Task(1, "task0", false.toString()))
    tasksLocalDataSource.insert(Task(2, "task1", true.toString()))
    tasksLocalDataSource.insert(Task(3, "task2", true.toString()))

    list = tasksLocalDataSource.findActive()
    assertThat(list).hasSize(1)
  }

  @Test
  fun insert() {
    var list = tasksLocalDataSource.find("task")
    assertThat(list).isEmpty()

    tasksLocalDataSource.insert(Task(1, "task0", false.toString()))
    list = tasksLocalDataSource.find("task")
    assertThat(list).hasSize(1)

    tasksLocalDataSource.insert(Task(1, "task01", true.toString()))
    list = tasksLocalDataSource.find("task")
    assertThat(list).hasSize(1)

    tasksLocalDataSource.insert(Task(2, "task1", true.toString()))
    tasksLocalDataSource.insert(Task(3, "task2", true.toString()))
    list = tasksLocalDataSource.find("task")
    assertThat(list).hasSize(3)
  }

  @Test
  fun update() {
    var list = tasksLocalDataSource.find("task")
    assertThat(list).isEmpty()

    tasksLocalDataSource.insert(Task(1, "task0", false.toString()))
    list = tasksLocalDataSource.findAll()
    assertThat(list).hasSize(1)
    assertThat(list[0].name).isEqualTo("task0")

    tasksLocalDataSource.update(Task(1, "task_updated", false.toString()))
    list = tasksLocalDataSource.findAll()
    assertThat(list).hasSize(1)
    assertThat(list[0].name).isEqualTo("task_updated")
  }

  @Test
  fun delete() {
    var list = tasksLocalDataSource.find("task")
    assertThat(list).isEmpty()

    tasksLocalDataSource.insert(Task(1, "task0", false.toString()))
    tasksLocalDataSource.insert(Task(2, "task1", false.toString()))
    tasksLocalDataSource.insert(Task(3, "task2", false.toString()))
    list = tasksLocalDataSource.find("task0")

    var target = list[0]
    tasksLocalDataSource.delete(target)
    list = tasksLocalDataSource.find("task0")
    assertThat(list).isEmpty()
  }

  @Test
  fun deleteAll() {
    var list = tasksLocalDataSource.find("task")
    assertThat(list).isEmpty()

    tasksLocalDataSource.insert(Task(1, "task0", false.toString()))
    tasksLocalDataSource.insert(Task(2, "task1", false.toString()))
    tasksLocalDataSource.insert(Task(3, "task2", false.toString()))
    tasksLocalDataSource.deleteAll()
    list = tasksLocalDataSource.findAll()
    assertThat(list).isEmpty()
  }
}