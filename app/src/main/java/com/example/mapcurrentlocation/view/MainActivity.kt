package com.example.mapcurrentlocation.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mapcurrentlocation.R
import com.example.mapcurrentlocation.model.MapModel
import com.example.mapcurrentlocation.presenter.MainPresenterImp
import com.example.mapcurrentlocation.presenter.interfaces.MainPresenter
import com.example.mapcurrentlocation.utils.Utils
import com.example.mapcurrentlocation.view.interfaces.MainView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions




class MainActivity : AppCompatActivity(), OnMapReadyCallback, MainView {

    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var mapFragment: SupportMapFragment? = null
    lateinit var tvStreet: TextView
    lateinit var tvRemAddress: TextView

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter = MainPresenterImp(this, MapModel(),this)

        tvStreet = findViewById(R.id.textView_street)
        tvRemAddress = findViewById(R.id.textView_remaining_addres)
        val ivUpdateLoc = findViewById<ImageView>(R.id.imageView_update_loc)
        ivUpdateLoc.setOnClickListener {
            updateLocation()
        }

        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)
        setAddress(latitude, longitude)

        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

    }

    private fun updateLocation() {
        presenter.updateLocation()
        Toast.makeText(this, getString(R.string.location_update), Toast.LENGTH_LONG).show()
    }

    private fun setAddress(latitude: Double, longitude: Double) {
        presenter.getAddress(this, latitude, longitude) { address ->
            tvStreet.text = address["street"]
            val addressText  = " ${address["colony"]}, ${address["locality"]}, ${address["zipCode"]}"
            tvRemAddress.text = addressText
        }
    }

    // overrides
    override fun onMapReady(googleMap: GoogleMap) {
        presenter.onMapReady(googleMap)
    }

    override fun onResume() {
        super.onResume()
        mapFragment?.onResume()
    }

    override fun onPause() {
        mapFragment?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapFragment?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapFragment?.onLowMemory()
    }

    override fun onBackPressed() {
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

}