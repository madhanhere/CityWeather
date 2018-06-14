package `in`.madhankumar.cityweather

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityEditText = findViewById<EditText>(R.id.city_edit_text)

        var forecastBtn = findViewById<Button>(R.id.forecast_btn)
        forecastBtn.setOnClickListener {
            val intent = Intent(this, ForecastActivity::class.java)
            intent.putExtra("searchTerm", cityEditText.text.toString())
            startActivity(intent)
        }
    }
}
