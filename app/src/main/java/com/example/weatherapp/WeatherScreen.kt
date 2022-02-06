package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_weather_screen.*

class WeatherScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_screen)

        cityName.text = intent.getStringExtra(MainActivity.CITY_NAME)
        temp.text = intent.getStringExtra(MainActivity.TEMP)
        condition.text = intent.getStringExtra(MainActivity.CONDITION)

    }
}