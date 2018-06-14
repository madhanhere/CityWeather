package `in`.madhankumar.cityweather

import `in`.madhankumar.cityweather.helper.Weather
import `in`.madhankumar.cityweather.helper.WeatherRetriever
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val searchTerm = intent.extras.getString("searchTerm")

        var retriever = WeatherRetriever()
        var callBack = object: Callback<Weather> {
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                println(response)
                title = response?.body()?.query?.results?.channel?.title
                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast
                var forecastStrings = mutableListOf<String>()
                if (forecasts !== null) {
                    for(forecast in forecasts) {
                        forecastStrings.add("${forecast.date} - High: ${forecast.high} Low: ${forecast.low} - ${forecast.text}")
                    }
                }
                var listView = findViewById<ListView>(R.id.forecast_recycler_view)

                var adapter = ArrayAdapter(this@ForecastActivity, android.R.layout.simple_list_item_1, forecastStrings)

                listView.adapter = adapter as ListAdapter?
            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("response fail")
            }

        }
        retriever.getForecast(callBack, searchTerm)
    }
}
