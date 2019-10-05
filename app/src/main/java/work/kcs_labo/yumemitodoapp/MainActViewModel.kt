package work.kcs_labo.yumemitodoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import work.kcs_labo.yumemitodoapp.data.source.TasksRepository

class MainActViewModel(app: Application, private val repository: TasksRepository) :
  AndroidViewModel(app) {
  val allBtnEnable = MutableLiveData<Boolean>(true)
  val activeBtnEnable = MutableLiveData<Boolean>(false)
  val completedBtnEnable = MutableLiveData<Boolean>(false)

  fun setBtnSelected(btnEnum: BtnEnum) {
    when (btnEnum) {
      BtnEnum.ALL -> {
        allBtnEnable.value = true
        activeBtnEnable.value = false
        completedBtnEnable.value = false
      }
      BtnEnum.ACTIVE -> {
        allBtnEnable.value = false
        activeBtnEnable.value = true
        completedBtnEnable.value = false
      }
      BtnEnum.COMPLETED -> {
        allBtnEnable.value = false
        activeBtnEnable.value = false
        completedBtnEnable.value = true
      }
    }
  }

  enum class BtnEnum {
    ALL, ACTIVE, COMPLETED
  }
}