package com.example.colan_infotech.api

import product_response_Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("users/geerlingguy/repos")
    fun products(@Query("per_page") count: Int?,@Query("page") pagecount: Int?): Call<List<product_response_Base?>?>?

}