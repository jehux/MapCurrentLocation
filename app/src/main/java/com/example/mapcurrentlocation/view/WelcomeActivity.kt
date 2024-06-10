package com.example.mapcurrentlocation.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mapcurrentlocation.R
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mapcurrentlocation.utils.Utils
import java.io.IOException
import java.util.Locale

class WelcomeActivity : AppCompatActivity() {

    val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnContinue = findViewById<Button>(R.id.btn_go_to_map)
        btnContinue.setOnClickListener {
            Utils.getLocation(this@WelcomeActivity) { location ->
                goTonMap(location.latitude, location.longitude)
            }
        }

    }

    private fun goTonMap(lat: Double, lon: Double) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("latitude", lat)
            putExtra("longitude", lon)
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Utils.getLocation(this@WelcomeActivity) { location ->
                    goTonMap(location.latitude, location.longitude)
                }
            } else {
                Toast.makeText(this, getString(R.string.permissions_information), Toast.LENGTH_LONG).show()
            }
        }
    }

}