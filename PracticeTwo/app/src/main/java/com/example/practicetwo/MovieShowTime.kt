package com.example.practicetwo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

    if(networkCapabilities != null){
        val hasInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        val toast = Toast.makeText(
            context,
            "Network availability checked and it is: $hasInternet",
            Toast.LENGTH_LONG).show()
        //Open log cat and see the message. try both connected and disconnected from internet.
        Log.e("Network", "Network availability checked and it is: $hasInternet")
        return hasInternet
    }
    else return false
}