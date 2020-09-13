package devenus.rodi.weather.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import devenus.rodi.weather.databinding.ItemWeatherBinding
import devenus.rodi.weather.databinding.ItemWeatherHeaderBinding
import devenus.rodi.weather.utils.second
import devenus.rodi.weather.utils.setHumidityText
import devenus.rodi.weather.utils.setTemperatureText
import devenus.rodi.weather.utils.urlGlide
import devenus.rodi.weather.view.WeatherAdapter.Companion.BASE_IMAGE_URL
import kotlin.math.roundToInt

class WeatherAdapter : ListAdapter<MainViewModel.ResultItem, RecyclerView.ViewHolder>(itemDiff) {

    companion object {
        const val HEADER = 0
        const val ITEM = 1
        const val BASE_IMAGE_URL = "https://www.metaweather.com/static/img/weather/png/"
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER
            else -> ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            HEADER -> {
                val binding = ItemWeatherHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return HeaderItemViewHolder(binding)
            }
            else -> {
                val binding = ItemWeatherBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ItemViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }
}

class HeaderItemViewHolder(private val binding: ItemWeatherHeaderBinding) :
    RecyclerView.ViewHolder(binding.root)

class ItemViewHolder(private val binding: ItemWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MainViewModel.ResultItem) {
        binding.apply {
            tvWeatherLocal.text = item.city

            tvTodayName.text = item.weatherList.first().weather
            tvTodayTemperature.setTemperatureText(item.weatherList.first().temperature.roundToInt())
            tvTodayHumidity.setHumidityText(item.weatherList.first().humidity)
            ivTodayIcon.urlGlide("$BASE_IMAGE_URL${item.weatherList.first().weatherImage}.png")

            tvTomorrowName.text = item.weatherList[1].weather
            tvTomorrowTemperature.setTemperatureText(item.weatherList.second().temperature.roundToInt())
            tvTomorrowHumidity.setHumidityText(item.weatherList.second().humidity)
            ivTomorrowIcon.urlGlide("$BASE_IMAGE_URL${item.weatherList.second().weatherImage}.png")
        }
    }
}

val itemDiff = object : DiffUtil.ItemCallback<MainViewModel.ResultItem>() {
    override fun areItemsTheSame(
        oldItem: MainViewModel.ResultItem,
        newItem: MainViewModel.ResultItem
    ): Boolean {
        return oldItem.city == newItem.city
    }

    override fun areContentsTheSame(
        oldItem: MainViewModel.ResultItem,
        newItem: MainViewModel.ResultItem
    ): Boolean {
        return oldItem == newItem
    }
}
