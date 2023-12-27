package com.singlepoint.weektvshow.ui.commonutils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    val networkCapabilities =
        connectivityManager.activeNetwork?.let { connectivityManager.getNetworkCapabilities(it) }


    return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}
