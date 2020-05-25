package com.techmahidra.optustest.ui.userinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.techmahidra.optustest.data.repository.UserInfoRepository
import com.techmahidra.optustest.data.response.AlbumListResponse
import com.techmahidra.optustest.data.response.UserInfoListResponse

class UserInfoViewModel : ViewModel(){
    // repository instance
    private val userInfoRepository = UserInfoRepository()

    // get user list from server and save in livedata
    val userInfoListLiveData: LiveData<List<UserInfoListResponse.UserInfoListResponseItem>> get() = userInfoRepository.getUserInfoListMutableLiveData()

    // clear repository jobs
    override fun onCleared() {
        super.onCleared()
        userInfoRepository.completableJob.cancel()
    }
}