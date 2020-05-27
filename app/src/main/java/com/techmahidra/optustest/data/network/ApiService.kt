package com.techmahidra.optustest.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.data.response.UserInfoListResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


// Api constants
const val API_GET_USER_INFO_LIST = "users"
const val API_GET_USER_ALBUM_LIST = "photos"
const val BASE_URL = "https://jsonplaceholder.typicode.com/"

/*
* ApiService - this interface helps to define multiple request as per need and create retrofit object to handle server call
* */
interface ApiService {
    // Get user info list api call
    @GET(API_GET_USER_INFO_LIST)
    fun getUserInfoList(): Deferred<UserInfoListResponse>

    // Get user album list api call
    @GET(API_GET_USER_ALBUM_LIST)
    fun getUserAlbumList(@Query("albumId") albumId: Int): Deferred<UserAlbumListResponse>


    // Create Retrofit object
    companion object {

        fun createCorService(): ApiService {

            // build okhttpclient obj
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            // build retrofit obj
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(ApiService::class.java)
        }
    }

}