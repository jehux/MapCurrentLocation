package com.example.mapcurrentlocation.presenter

import android.app.Activity
import android.content.Context
import com.example.mapcurrentlocation.R
import com.example.mapcurrentlocation.model.MapModel
import com.example.mapcurrentlocation.presenter.interfaces.MainPresenter
import com.example.mapcurrentlocation.utils.Utils
import com.example.mapcurrentlocation.view.interfaces.MainView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainPresenterImp(private val view: MainView,
                       private val model: MapModel,
                       private val context: Activity
                        ): MainPresenter {

    lateinit var googleMap: GoogleMap
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    override fun getAddress(
        context: Context,
        latitude: Double,
        longitude: Double,
        callback: (Map<String, String>) -> Unit
    ) {
        this.latitude = latitude
        this.longitude = longitude
        model.getLocationData(context, latitude, longitude) {
            if (it["colony"]?.isEmpty() == true) {
                view.showError(context.getString(R.string.err_address))
            }
            callback(it)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.clear()
        val currentLocation = LatLng(latitude, longitude)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16f))
        googleMap.addMarker(
            MarkerOptions()
            .position(currentLocation)
            .title(context.getString(R.string.my_location))
            .icon(BitmapDescriptorFactory.fromBitmap(Utils.getMapIconBitmap(context)))
        )

        googleMap.setOnMapLoadedCallback {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 18f))
        }
    }

    override fun updateLocation() {
        Utils.getLocation(context) {
            val currentLocation = LatLng(it.latitude, it.longitude)
            googleMap.clear()
            googleMap.addMarker(MarkerOptions()
                .position(currentLocation)
                .title(context.getString(R.string.my_location))
                .icon(BitmapDescriptorFactory.fromBitmap(Utils.getMapIconBitmap(context)))
            )
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 18f))
        }
    }

}