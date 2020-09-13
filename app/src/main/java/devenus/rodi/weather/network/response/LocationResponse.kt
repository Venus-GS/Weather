package devenus.rodi.weather.network.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("title")
    val city: String,
    @SerializedName("woeid")
    val woeId: Int
)
