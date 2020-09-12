package devenus.rodi.weather.data

import devenus.rodi.weather.network.response.LocationResponse
import devenus.rodi.weather.network.response.LocationWeatherResponse
import devenus.rodi.weather.network.service.WeatherService

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {

    override fun getLocationList(): List<LocationResponse> {
        TODO("Not yet implemented")
    }

    override fun getLocationWeather(): LocationWeatherResponse {
        TODO("Not yet implemented")
    }
}

interface WeatherRepository {

    fun getLocationList() : List<LocationResponse>

    fun getLocationWeather() : LocationWeatherResponse

}