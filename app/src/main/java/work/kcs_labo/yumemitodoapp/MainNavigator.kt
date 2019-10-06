package work.kcs_labo.yumemitodoapp

import work.kcs_labo.yumemitodoapp.data.Task

interface MainNavigator {
  fun onTaskClick(task: Task)
  fun onDeleteClick(task: Task)
}