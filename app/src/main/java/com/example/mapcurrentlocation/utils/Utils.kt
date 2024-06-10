package com.example.mapcurrentlocation.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mapcurrentlocation.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

object Utils {

    val LOCATION_PERMISSION_REQUEST_CODE = 1

    fun getLocation(activity: Activity, callback: (Location) -> Unit) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Don't have permissions, request it
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
            val task: Task<Location> = fusedLocationProviderClient.lastLocation
            task.addOnSuccessListener { location: Location? ->
                location?.let {
                    callback(location)
                }
            }
        }
    }

    fun getMapIconBitmap(context: Context): Bitmap {
        val resId = R.drawable.ic_dot_location
        val width = 78
        val height = 79
        val imageBitmap = BitmapFactory.decodeResource(context.resources, resId)
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false)
    }

    fun createStringDic(vararg pairs: Pair<String, String>): Map<String, String> {
        return mapOf(*pairs)
    }

}