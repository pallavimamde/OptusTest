package com.techmahidra.optustest.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.HttpException
import com.techmahidra.optustest.data.network.ApiService
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.data.response.UserInfoListResponse
import com.techmahidra.optustest.ui.userinfo.UserInfoActivity.Companion.selectedUserId
import kotlinx.coroutines.*


/*
* UserInfoRepository - This class helps to maintain server call and handle the server response
* */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UserInfoRepository() {


    // mutable objects for userinfo list
    private var user = mutableListOf<UserInfoListResponse.UserInfoListResponseItem>()
    private var userInfoListMutableLiveData = MutableLiveData<List<UserInfoListResponse.UserInfoListResponseItem>>()
    private var userAlbum = mutableListOf<UserAlbumListResponse.AlbumListResponseItem>()
    private var userAlbumListMutableLiveData = MutableLiveData<List<UserAlbumListResponse.AlbumListResponseItem>>()


    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private val TAG = "EmployeeRepository"


    // api service lazy call
    private val thisApiCorService by lazy {
        ApiService.createCorService()
    }

    // get user information list from server and check response
    fun getUserInfoListMutableLiveData(): MutableLiveData<List<UserInfoListResponse.UserInfoListResponseItem>> {
        coroutineScope.launch {
            val request = thisApiCorService.getUserInfoList()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response != null) {
                        user = response
                        userInfoListMutableLiveData.value = user
                    } else {
                        Log.e(TAG, "Response is null")
                    }

                } catch (e: HttpException) {
                    Log.e(TAG, e.message)

                } catch (e: Throwable) {
                    Log.e(TAG, e.message)
                }
            }
        }
        return userInfoListMutableLiveData
    }

    // get user information list from server and check response
    fun getUserAlbumListMutableLiveData(): MutableLiveData<List<UserAlbumListResponse.AlbumListResponseItem>> {
        coroutineScope.launch {
            val request = thisApiCorService.getUserAlbumList(selectedUserId)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response != null) {
                        userAlbum = response
                        userAlbumListMutableLiveData.value = userAlbum
                    } else {
                        Log.e(TAG, "Response is null")
                    }

                } catch (e: HttpException) {
                    Log.e(TAG, e.message)

                } catch (e: Throwable) {
                    Log.e(TAG, e.message)
                }
            }
        }
        return userAlbumListMutableLiveData
    }
}