package com.aytlo.tony.aviasales.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aytlo.tony.aviasales.R
import com.aytlo.tony.aviasales.data.source.remote.AviasalesRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {


    private val api: AviasalesRepository = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val response = api.getCities("mow")

            withContext(Dispatchers.Main) {
                tvText.text = response.cities.toString()
            }
        }
    }
}
