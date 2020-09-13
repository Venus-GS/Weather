package devenus.rodi.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WeatherApplication : Application() {

    companion object {
        private var instance: WeatherApplication? = null

        fun getInstance(): WeatherApplication {
            return if (instance == null) WeatherApplication() else instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }
}