package work.kcs_labo.yumemitodoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import work.kcs_labo.yumemitodoapp.data.source.TasksRepository

class MainActViewModel(app:Application, repository: TasksRepository) : AndroidViewModel(app) {
}