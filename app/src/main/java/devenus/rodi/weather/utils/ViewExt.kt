package devenus.rodi.weather.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.toSpannable
import devenus.rodi.weather.R

fun ImageView.urlGlide(url: String) {
    GlideApp.with(this)
        .load(url)
        .fitCenter()
        .into(this)
}

fun TextView.setTemperatureText(temperature: Int) {
    this.textPartBold(resources.getString(R.string.temperature, temperature), temperature.toString())
}

fun TextView.setHumidityText(humidity: Int) {
    this.textPartBold(resources.getString(R.string.humidity, humidity), humidity.toString())
}

fun TextView.textPartBold(fullText: String, boldPartText: String) {
    val index = fullText.indexOf(boldPartText)

    this.text = fullText.toSpannable().apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            index,
            index + boldPartText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}
