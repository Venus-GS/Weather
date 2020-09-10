package devenus.rodi.weather.network.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("woeid")
    val woeId: Int
)