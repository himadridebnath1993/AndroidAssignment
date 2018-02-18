package com.androidassignment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.androidassignment.service.mAsyncTask
import com.androidassignment.service.mServiceUrl
import org.json.JSONObject
import com.androidassignment.service.AnimalBreeds
import com.androidassignment.service.mWebService
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager




class MainActivity : AppCompatActivity() {

    lateinit var dataList: ArrayList<AnimalBreeds>
    lateinit var rv_listData:RecyclerView
    lateinit var mBreedsAdapter: AnimalsBreedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataList=ArrayList()
        rv_listData=findViewById(R.id.rv_listData)

        mBreedsAdapter = AnimalsBreedsAdapter(dataList)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        rv_listData.setLayoutManager(mLayoutManager)
        rv_listData.setItemAnimator(DefaultItemAnimator())
        rv_listData.setAdapter(mBreedsAdapter)


        if(mWebService().checkInternetConnection(this)){
            prepareDataSet()
        }else{
            Toast.makeText(this,"Network connection error",Toast.LENGTH_LONG).show()
        }

    }

    private fun prepareDataSet(){

        mAsyncTask(this, ""+mServiceUrl.AnimalsList, object : mAsyncTask.AsyncResponse {
            override fun processFinish(output: String) {

                dataList.clear()
                val response : JSONObject = JSONObject(output)

                val message= response.getJSONObject("message")

                val keys = message.keys()
                val a = keys.iterator()
                while (a.hasNext()) {
                    val key = a.next() as String
                    // loop to get the dynamic key

                    val jsnArr =message.getJSONArray(key)
                    val listdata : ArrayList<String> = ArrayList()

                    if(jsnArr.length()>0){
                        for (i in 0 until jsnArr.length()) {
                            listdata.add(jsnArr.getString(i))
                        }
                        dataList.add(element = AnimalBreeds(key,true,listdata))
                    }else{
                        dataList.add(element = AnimalBreeds(key,false,listdata))
                    }
                }
                mBreedsAdapter.notifyDataSetChanged()
            }
        }).execute()
    }





}
