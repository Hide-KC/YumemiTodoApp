package work.kcs_labo.yumemitodoapp.list

import work.kcs_labo.yumemitodoapp.MainNavigator
import work.kcs_labo.yumemitodoapp.data.Task

class TaskModel(val task: Task) {
  private var navigator: MainNavigator? = null

  fun taskClick(task: Task) {
    navigator?.onTaskClick(task)
  }

  fun deleteClick(task: Task) {
    navigator?.onDeleteClick(task)
  }

  fun setNavigator(navigator: MainNavigator?){
    this.navigator = navigator
  }
}