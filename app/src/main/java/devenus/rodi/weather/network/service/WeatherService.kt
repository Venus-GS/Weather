package devenus.rodi.weather.network.service

import devenus.rodi.weather.network.response.LocationResponse
import devenus.rodi.weather.network.response.LocationWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("/location/search/")
    suspend fun getLocationList(@Query("query") searchWord: String): List<LocationResponse>

    @GET("/location/{woeId}/")
    suspend fun getWeather(@Path("woeId") woeId: Int) : LocationWeatherResponse
}