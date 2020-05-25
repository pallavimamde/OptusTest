package com.techmahidra.optustest.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.techmahidra.optustest.data.response.UserInfoListResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


// Api constants
const val API_GET_USER_INFO_LIST = "users"
const val BASE_URL = "https://jsonplaceholder.typicode.com/"


interface ApiService {

    // Get user list api call
    @GET(API_GET_USER_INFO_LIST)
    fun getUserInfoList(): Deferred<UserInfoListResponse>

    // Create Retrofit object
    companion object {

        fun createCorService(): ApiService {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(ApiService::class.java)
        }
    }
}