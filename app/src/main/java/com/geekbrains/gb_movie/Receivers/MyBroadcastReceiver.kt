package com.geekbrains.gb_movie.Receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData

class MyBroadcastReceiver : BroadcastReceiver() {
    companion object {
        private val flag: MutableLiveData<Boolean> = MutableLiveData()
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        when(checkInternet(context)){
            true-> flag.value=false
            false->flag.value=true
        }
    }

    fun checkInternet(context: Context?): Boolean {
        context?.let {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            activeNetwork?.let {
                return activeNetwork.isConnectedOrConnecting
            } ?: return false
        }?: return false
    }
}