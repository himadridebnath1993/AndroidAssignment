package com.androidassignment.service

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask

import java.util.HashMap

/**
 * Created by himadri on 16/1/16.
 */
class mAsyncTask(internal var context: Context, internal var url: String,
                 delegate: AsyncResponse) : AsyncTask<Void, Void, String>() {

    lateinit var respond : String
    lateinit  var pd: ProgressDialog
    var delegate: AsyncResponse? = delegate
    var isDialogShow = true


    override fun onPreExecute() {
        super.onPreExecute()
        try {
            if (isDialogShow) {
                pd = ProgressDialog(context)
                pd.setMessage("Please wait...")
                pd.setCancelable(false)
                pd.setCanceledOnTouchOutside(false)
                pd.show()
            }
        } catch (e: Exception) { }
    }

    override fun doInBackground(vararg params: Void): String? {
        try {
            respond = mWebService()[url]
            respond = respond.replace("null".toRegex(), "\"\"")

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        try {
            delegate!!.processFinish(respond)
            if (isDialogShow) {
                if (pd.isShowing)
                    pd.dismiss()
            }
        } catch (e: Exception) { }

    }


    // you may separate this or combined to caller class.
    interface AsyncResponse {
        fun processFinish(output: String)
    }
}
