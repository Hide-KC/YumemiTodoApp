package work.kcs_labo.yumemitodoapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

    setUpWidgets()
  }

  private fun setUpWidgets() {
    val viewModel = obtainViewModel()

    new_task.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {

      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })

    add_btn.setOnClickListener {

    }

    all_btn.setOnClickListener {
      viewModel.setBtnSelected(MainActViewModel.BtnEnum.ALL)
    }

    active_btn.setOnClickListener {
      viewModel.setBtnSelected(MainActViewModel.BtnEnum.ACTIVE)
    }

    complete_btn.setOnClickListener {
      viewModel.setBtnSelected(MainActViewModel.BtnEnum.COMPLETED)
    }
  }

  fun obtainViewModel() = obtainViewModel(MainActViewModel::class.java)
}
