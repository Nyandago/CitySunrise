package com.cannybits.citysunrise

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

  fun getSunRise(view:View){

        val city = etCityName.text.toString()
        val url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/weatherdata/forecast?aggregateHours=24&combinationMethod=aggregate&includeAstronomy=true&contentType=json&unitGroup=metric&locationMode=array&key=FDCBGBJRS3453U5YRFWJ82XSB&dataElements=default&locations="+
                city
        MyAsyncTask().execute(url)
    }

    inner class MyAsyncTask : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {
            try {

                val url= URL(params[0])

                val urlConnect=url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout=7000

                var inString= ConvertStreamToString(urlConnect.inputStream)
                //Cannot access to ui
                publishProgress(inString)
            }catch (ex:Exception){}


            return " "
        }

        override fun onPreExecute() {
            //before task has start
        }

        override fun onPostExecute(result: String?) {
           // after task is done
        }

        override fun onProgressUpdate(vararg values: String?) {
            try{
                var json= JSONObject(values[0])
                val query=json.getJSONObject("query")
                val results=query.getJSONObject("results")
                val channel=results.getJSONObject("channel")
                val astronomy=channel.getJSONObject("astronomy")
                var sunrise=astronomy.getString("sunrise")
                tvSunrise.text = " Sunrise time is "+ sunrise



            }catch (ex:Exception){}
        }

        override fun onCancelled(result: String?) {

        }

        override fun onCancelled() {

        }
    }

    fun ConvertStreamToString(inputStream: InputStream):String{

        val bufferReader= BufferedReader(InputStreamReader(inputStream))
        var line:String
        var AllString:String=""

        try {
            do{
                line=bufferReader.readLine()
                if(line!=null){
                    AllString+=line
                }
            }while (line!=null)
            inputStream.close()
        }catch (ex:Exception){}



        return AllString
    }

}