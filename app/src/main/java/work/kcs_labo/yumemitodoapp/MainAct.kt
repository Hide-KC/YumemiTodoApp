package work.kcs_labo.yumemitodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.main_act.*
import work.kcs_labo.yumemitodoapp.databinding.MainActBinding
import work.kcs_labo.yumemitodoapp.util.obtainViewModel

class MainAct : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DataBindingUtil.setContentView<MainActBinding>(this, R.layout.main_act)
        .also {
          it.viewModel = obtainViewModel()
          it.lifecycleOwner = this
        }

    add_btn.setOnClickListener {

    }
  }

  fun obtainViewModel() = obtainViewModel(MainActViewModel::class.java)
}
