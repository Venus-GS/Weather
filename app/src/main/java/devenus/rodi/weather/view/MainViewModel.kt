package devenus.rodi.weather.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import devenus.rodi.weather.base.BaseViewModel
import devenus.rodi.weather.data.WeatherRepository
import devenus.rodi.weather.network.response.ConsolidatedWeather
import devenus.rodi.weather.utils.EventLiveData
import devenus.rodi.weather.utils.MutableEventLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    companion object {
        const val SEARCH_WORD = "se"
    }

    private var resultItemList = mutableListOf<ResultItem>()

    private val _resultList = MutableLiveData<List<ResultItem>>()
    val resultList : LiveData<List<ResultItem>>
        get() = _resultList

    private val _onSwipeRefresh = MutableEventLiveData<Unit>()
    val onSwipeRefresh: EventLiveData<Unit>
        get() = _onSwipeRefresh

    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            weatherRepository.getLocationList(SEARCH_WORD)
                .onStart { _loading.value = true }
                .catch { throwable ->
                    Timber.e(throwable)
                }
                .collect { locationList ->
                    if (locationList.isNotEmpty()) {
                        resultItemList.add(ResultItem())
                        val results = locationList.map {
                            async {
                                getLocationWeather(it.city, it.woeId)
                            }
                        }
                        results.awaitAll()
                        _resultList.value = resultItemList
                        _loading.value = false
                    }
                }
        }
    }

    private suspend fun getLocationWeather(city: String, woeId: Int) {
        weatherRepository.getLocationWeather(woeId)
            .catch { throwable ->
                Timber.e(throwable)
            }
            .collect { weatherList ->
                resultItemList.add(ResultItem(city, weatherList))
            }
    }

    fun onSwipeRefresh() {
        _onSwipeRefresh.event = Unit
        getWeather()
    }

    data class ResultItem(
        val city: String = "",
        val weatherList: List<ConsolidatedWeather> = arrayListOf()
    )
}