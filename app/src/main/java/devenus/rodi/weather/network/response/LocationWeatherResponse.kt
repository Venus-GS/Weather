package devenus.rodi.weather.network.response

import com.google.gson.annotations.SerializedName

data class LocationWeatherResponse(
    @SerializedName("weather_state_name")
    val weather: String,
    @SerializedName("weather_state_abbr")
    val weatherImage: String,
    @SerializedName("the_temp")
    val temperature: Float,
    @SerializedName("humidity")
    val humidity: Int
)