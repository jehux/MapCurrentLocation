package com.example.mapcurrentlocation.presenter.interfaces

import android.content.Context
import com.google.android.gms.maps.GoogleMap

interface MainPresenter {

    fun getAddress(context: Context,
                   latitude: Double,
                   longitude: Double,
                   callback: (Map<String, String>) -> Unit)

    fun onMapReady(googleMap: GoogleMap)

    fun updateLocation()

}