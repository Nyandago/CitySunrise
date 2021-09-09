package com.cannybits.citysunrise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

  fun GetSunRise(view:View){
        var city = "sunnyvale,ca"
        val url = "https://weather-ydn-yql.media.yahoo.com/forecastrss?location="+city+"&format=json"
    }
}