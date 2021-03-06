package com.techmahidra.optustest.ui.userinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.techmahidra.optustest.data.repository.UserInfoRepository
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.data.response.UserInfoListResponse

/*
* UserInfoViewModel - Viewmodel class to responsible for preparing and managing data(response)
* */
class UserInfoViewModel : ViewModel() {
    companion object {
        lateinit var callbackResponse: String
    }

    // repository instance
    private val userInfoRepository = UserInfoRepository()

    // get user info list from server and save in livedata , return data to fragment using observer
    val userInfoListLiveData: LiveData<List<UserInfoListResponse.UserInfoListResponseItem>>
        get() = userInfoRepository.getUserInfoListMutableLiveData(
            object : UserInfoRepository.RetryableCallback {
                override fun onSuccess(success: String) {
                    callbackResponse = success
                }

                override fun onFailure(error: String) {
                    callbackResponse = error
                }
            })

    // get user album list from server and save in livedata , return data to fragment using observer
    val userAlbumListLiveData: LiveData<List<UserAlbumListResponse.AlbumListResponseItem>>
        get() = userInfoRepository.getUserAlbumListMutableLiveData(object :
            UserInfoRepository.RetryableCallback {
            override fun onSuccess(success: String) {
                callbackResponse = "Success"
            }

            override fun onFailure(error: String) {
                callbackResponse = error
            }
        })

    // clear repository jobs
    override fun onCleared() {
        super.onCleared()
        userInfoRepository.completableJob.cancel()
    }
}