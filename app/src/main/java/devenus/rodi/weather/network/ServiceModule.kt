package devenus.rodi.weather.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import devenus.rodi.weather.network.service.WeatherService
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
class ServiceModule {

    @Provides
    fun provideWeatherService(retrofit: Retrofit) : WeatherService =
        retrofit.create(WeatherService::class.java)
}