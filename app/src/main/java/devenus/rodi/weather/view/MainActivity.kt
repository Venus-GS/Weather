package devenus.rodi.weather.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import devenus.rodi.weather.R
import devenus.rodi.weather.base.BaseActivity
import devenus.rodi.weather.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel : MainViewModel by viewModels()

    private val adapter : WeatherAdapter by lazy { WeatherAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            viewModel = this@MainActivity.viewModel
            rvWeather.adapter = adapter
        }

        viewModel.resultList.observe(this, Observer {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            }
        })
    }
}