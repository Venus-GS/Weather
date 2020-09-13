package devenus.rodi.weather.data

import devenus.rodi.weather.network.response.ConsolidatedWeather
import devenus.rodi.weather.network.response.LocationResponse
import devenus.rodi.weather.network.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {

    override fun getLocationList(searchWord: String): Flow<List<LocationResponse>> {
        return flow {
            emit(weatherService.getLocationList(searchWord))
        }.flowOn(Dispatchers.IO)
    }

    override fun getLocationWeather(woeId: Int): Flow<List<ConsolidatedWeather>> {
        return flow {
            emit(weatherService.getWeather(woeId).consolidatedWeather)
        }.flowOn(Dispatchers.IO)
    }
}

interface WeatherRepository {

    fun getLocationList(searchWord: String) : Flow<List<LocationResponse>>

    fun getLocationWeather(woeId: Int) : Flow<List<ConsolidatedWeather>>
}