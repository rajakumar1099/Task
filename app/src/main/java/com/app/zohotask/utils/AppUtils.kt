package com.app.zohotask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


object AppUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        var isOnline = false
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            isOnline = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
                capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            } else {
                val activeNetworkInfo = manager.activeNetworkInfo
                activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return isOnline
    }
}