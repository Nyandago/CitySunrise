package com.cannybits.citysunrise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

  fun getSunRise(view:View){
        val city = etCityName.text.toString()
        val url = "https://weather-ydn-yql.media.yahoo.com/forecastrss?location="+city+"&format=json"
    }
}