package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val CITY_NAME = "cityName"
        const val TEMP = "temp"
        const val CONDITION = "condition"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_round_message_24)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    }

    fun addCity(view: View) {
        val str = input.text.toString().trim()

        val url = "http://api.weatherapi.com/v1/current.json?key=16c0000263794bf4bfa84847211107&q=$str&aqi=yes"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val intent = Intent(this,WeatherScreen::class.java)
                intent.putExtra(CITY_NAME,it.getJSONObject("location").getString("name"))
                intent.putExtra(TEMP,it.getJSONObject("current").getString("temp_c").toString())
                intent.putExtra(CONDITION,it.getJSONObject("current").getJSONObject("condition").getString("text"))
                startActivity(intent)
                finish()
            },
            {
                Toast.makeText(this,"Please enter a valid City!",Toast.LENGTH_SHORT).show()
            }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}