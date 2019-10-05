package work.kcs_labo.yumemitodoapp.util


import android.widget.Button
import androidx.databinding.BindingAdapter
import work.kcs_labo.yumemitodoapp.R

@BindingAdapter("bind:btnState")
fun Button.setBtnSelected(isSelected: Boolean?) {
  background = if (isSelected != null && isSelected) {
    resources.getDrawable(R.drawable.selected_btn, null)
  } else {
    resources.getDrawable(R.drawable.not_selected_btn, null)
  }
}