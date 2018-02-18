package com.androidassignment.service


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import okhttp3.OkHttpClient
import okhttp3.Request


/**
 * Created by himadri on 18/2/18.
 */
class mWebService {

    var client = OkHttpClient()


    @Throws(Exception::class)
    operator fun get(url: String): String {
        println("ServiceURL===>>>" + url)
        val request = Request.Builder()
                .url(url)
                .build()
        println("**************** GET URL *****************\n" + request)

        val response = client.newCall(request).execute()
        val r = response.body()!!.string()
        println("**************** GET RESPONSE *****************\n" + r)

        return r
    }


    @SuppressLint("MissingPermission")
    fun checkInternetConnection(context: Context): Boolean {
        val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo.isAvailable && conMgr.activeNetworkInfo.isConnected) {
            return true
        } else {

            println("Internet Connection Not Present")
            return false
        }
    }
}

