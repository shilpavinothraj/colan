package com.example.colan_infotech.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.colan_infotech.api.APIInterface
import com.example.colan_infotech.api.NetworkService
import okhttp3.ResponseBody
import org.json.JSONObject
import product_response_Base
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel() {
    var productlistResponsedata = MutableLiveData<List<product_response_Base?>>()
    var globalcartresponsejson = JSONObject()

    fun productList(){
        Log.d("apicall","yes")
        val cartAPI = NetworkService.instance.create(APIInterface::class.java)
        val getProductsRequest: Call<List<product_response_Base?>?>? = cartAPI.products(10,1)
        getProductsRequest?.enqueue(object : Callback<List<product_response_Base?>?> {
            override fun onFailure(call: Call<List<product_response_Base?>?>, t: Throwable) {
                Log.d("apicall", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<product_response_Base?>?>,
                response: Response<List<product_response_Base?>?>
            ) {
                Log.d("apicall","got")

                productlistResponsedata.value=response.body()
            }

        })

    }
}