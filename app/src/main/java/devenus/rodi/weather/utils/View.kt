package devenus.rodi.weather.utils

import android.widget.ImageView

fun ImageView.urlGlide(url: String) {
    GlideApp.with(this)
        .load(url)
        .fitCenter()
        .into(this)
}