package com.techmahidra.optustest.ui.userinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techmahidra.optustest.data.network.ApiService
import com.techmahidra.optustest.data.repository.UserInfoRepository
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.data.response.UserInfoListResponse
import com.techmahidra.optustest.ui.userinfo.viewmodel.UserInfoViewModel
import io.reactivex.Maybe
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class UserInfoListFragmentTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var retryableCallback: UserInfoRepository.RetryableCallback
  //  lateinit var mApiInterface: ApiService
    lateinit var userInfoViewModel: UserInfoViewModel
    lateinit var mRepositoryViewModel: UserInfoRepository
    private lateinit var apiService : ApiService


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mRepositoryViewModel = UserInfoRepository()
        this.userInfoViewModel = UserInfoViewModel()
      //  thisApiCorService =  ApiService.createCorService()
        apiService = Mockito.mock(ApiService::class.java)
        retryableCallback = Mockito.mock(UserInfoRepository.RetryableCallback::class.java)

    }

    @Test
        fun getUserInfoDataSuccess() {

            Mockito.`when`(apiService.getUserInfoList()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }
        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserInfoListResponse.UserInfoListResponseItem>>
        this.userInfoViewModel.userInfoListLiveData.observeForever(observer)
        this.mRepositoryViewModel.getUserInfoListMutableLiveData(retryableCallback)
        Thread.sleep(7000)
        Assert.assertNotNull(this.userInfoViewModel.userInfoListLiveData.value)
    }

    @Test
    fun getAlbumDataSuccess() {
        Mockito.`when`(apiService.getUserAlbumList(1)).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }
        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserAlbumListResponse.AlbumListResponseItem>>
        this.userInfoViewModel.userAlbumListLiveData.observeForever(observer)
        this.mRepositoryViewModel.getUserInfoListMutableLiveData(retryableCallback)
        Thread.sleep(7000)
        Assert.assertNotNull(this.userInfoViewModel.userAlbumListLiveData.value)
    }


    @Test
    fun getUserInfoDataFail() {

        Mockito.`when`(apiService.getUserInfoList()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }
        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserInfoListResponse.UserInfoListResponseItem>>
        this.userInfoViewModel.userInfoListLiveData.observeForever(observer)
        this.mRepositoryViewModel.getUserInfoListMutableLiveData(retryableCallback)
        Thread.sleep(7000)
        Assert.assertNull(this.userInfoViewModel.userInfoListLiveData.value)
    }

    @Test
    fun getAlbumDataFail() {
        Mockito.`when`(apiService.getUserAlbumList(1)).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }
        val observer = Mockito.mock(Observer::class.java) as Observer<List<UserAlbumListResponse.AlbumListResponseItem>>
        this.userInfoViewModel.userAlbumListLiveData.observeForever(observer)
        this.mRepositoryViewModel.getUserInfoListMutableLiveData(retryableCallback)
        Thread.sleep(7000)
        Assert.assertNull(this.userInfoViewModel.userAlbumListLiveData.value)
    }



}