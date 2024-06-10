package com.example.mapcurrentlocation.model

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.example.mapcurrentlocation.utils.Utils
import java.io.IOException
import java.util.Locale


class MapModel {
    fun getLocationData(context: Context,
                        latitude: Double,
                        longitude: Double,
                        callback: (Map<String, String>) -> Unit) {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val allAddress = geocoder.getFromLocation(latitude, longitude, 1)
            if (allAddress != null) {
                if (allAddress.isNotEmpty()) {
                    val address = allAddress[0]
                    val street = address.thoroughfare ?: "Nombre de calle no disponible"
                    val zipCode = address.postalCode ?: "Código postal no disponible"
                    val locality = address.locality ?: "Localidad no existe"
                    val colony = address.subLocality ?: "Colonia no existe"

                    val mapAdd = Utils.createStringDic(
                        "street" to street,
                        "zipCode" to zipCode,
                        "locality" to locality,
                        "colony" to colony
                    )
                    return callback(mapAdd)
                } else {
                    val mapAdd = Utils.createStringDic(
                        "street" to "Nombre de calle no disponible",
                    )
                    return callback(mapAdd)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}