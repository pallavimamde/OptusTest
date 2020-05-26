package com.techmahidra.optustest.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.techmahidra.optustest.R
import com.techmahidra.optustest.core.UserInfoApplication
import com.techmahidra.optustest.data.network.ApiService
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.data.response.UserInfoListResponse
import com.techmahidra.optustest.ui.userinfo.UserInfoActivity.Companion.selectedUserId
import kotlinx.coroutines.*
import retrofit2.HttpException


/*
* UserInfoRepository - This class helps to maintain server call and handle the server response and
*  return response to the viewmodel class
* */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UserInfoRepository() {


    // mutable objects helps to save the server response in the form of different response type
    private var user = mutableListOf<UserInfoListResponse.UserInfoListResponseItem>()
    private var userInfoListMutableLiveData =
        MutableLiveData<List<UserInfoListResponse.UserInfoListResponseItem>>()
    private var userAlbum = mutableListOf<UserAlbumListResponse.AlbumListResponseItem>()
    private var userAlbumListMutableLiveData =
        MutableLiveData<List<UserAlbumListResponse.AlbumListResponseItem>>()


    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)


    // api service lazy call
    private val thisApiCorService by lazy {
        ApiService.createCorService()
    }

    // get user info list list from server and check response, return the response to the respective caller method of viewmodel
    fun getUserInfoListMutableLiveData(param: RetryableCallback): MutableLiveData<List<UserInfoListResponse.UserInfoListResponseItem>> {
        coroutineScope.launch {
            val request = thisApiCorService.getUserInfoList()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response != null) {
                        param.onSuccess(
                            UserInfoApplication.applicationContext().resources.getString(
                                R.string.success
                            )
                        )
                        user = response
                        userInfoListMutableLiveData.value = user
                    } else {
                        param.onFailure(
                            UserInfoApplication.applicationContext().resources.getString(
                                R.string.fail
                            )
                        )
                    }

                } catch (e: HttpException) {
                    e.message?.let { param.onFailure(it) }

                } catch (e: Throwable) {
                    e.message?.let { param.onFailure(it) }
                }
            }
        }
        return userInfoListMutableLiveData
    }

    // get user album list from server and check response, return the response to the respective caller method of the viewmodel
    fun getUserAlbumListMutableLiveData(param: RetryableCallback): MutableLiveData<List<UserAlbumListResponse.AlbumListResponseItem>> {
        coroutineScope.launch {
            val request = thisApiCorService.getUserAlbumList(selectedUserId)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response != null) {
                        param.onSuccess(
                            UserInfoApplication.applicationContext().resources.getString(
                                R.string.success
                            )
                        )
                        userAlbum = response
                        userAlbumListMutableLiveData.value = userAlbum
                    } else {
                        param.onFailure(
                            UserInfoApplication.applicationContext().resources.getString(
                                R.string.fail
                            )
                        )
                    }

                } catch (e: HttpException) {
                    e.message?.let { param.onFailure(it) }

                } catch (e: Throwable) {
                    e.message?.let { param.onFailure(it) }
                }
            }
        }
        return userAlbumListMutableLiveData
    }

    // Call back interface to handle response type
    interface RetryableCallback {
        fun onSuccess(success: String)
        fun onFailure(error: String)
    }
}