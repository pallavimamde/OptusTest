package com.techmahidra.optustest.ui.userinfo.viewmodel

import androidx.lifecycle.Observer
import com.techmahidra.optustest.data.repository.UserInfoRepository
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.techmahidra.optustest.R
import com.techmahidra.optustest.core.UserInfoApplication
import com.techmahidra.optustest.data.network.ApiService
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.data.response.UserInfoListResponse
import io.reactivex.Maybe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.Mockito.*


@RunWith(JUnit4::class)
internal class UserInfoViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var userInfoRepository: UserInfoRepository
    private lateinit var retryableCallback: UserInfoRepository.RetryableCallback
    private lateinit var userInfoViewModel: UserInfoViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userInfoRepository = UserInfoRepository()
        userInfoViewModel = UserInfoViewModel()
    }

    @Test
    fun fetchRepositories_positiveResponseUserInfo() {
        `when`(this.userInfoRepository.getUserInfoListMutableLiveData()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }
        val observer =
            mock(Observer::class.java) as Observer<List<UserInfoListResponse.UserInfoListResponseItem>>
        this.userInfoViewModel.userInfoListLiveData.observeForever(observer)
        this.retryableCallback.onSuccess(
            UserInfoApplication.applicationContext().resources.getString(
                R.string.success
            )
        )
        assertNotNull(this.userInfoViewModel.userInfoListLiveData.value)
    }

    @Test
    fun fetchRepositories_positiveResponseUserAlbum() {
        `when`(this.userInfoRepository.getUserAlbumListMutableLiveData(this.retryableCallback)).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }
        val observer =
            mock(Observer::class.java) as Observer<List<UserAlbumListResponse.AlbumListResponseItem>>
        this.userInfoViewModel.userAlbumListLiveData.observeForever(observer)
        this.retryableCallback.onSuccess(
            UserInfoApplication.applicationContext().resources.getString(
                R.string.success
            )
        )
        assertNotNull(this.userInfoViewModel.userAlbumListLiveData.value)
    }

    @Test
    fun fetchRepositories_errorResponseUserInfo() {
        `when`(this.userInfoRepository.getUserAlbumListMutableLiveData(this.retryableCallback)).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }
        val observer =
            mock(Observer::class.java) as Observer<List<UserAlbumListResponse.AlbumListResponseItem>>
        this.userInfoViewModel.userAlbumListLiveData.observeForever(observer)
        this.retryableCallback.onSuccess(
            UserInfoApplication.applicationContext().resources.getString(
                R.string.fail
            )
        )
        assertNotNull(this.userInfoViewModel.userAlbumListLiveData.value)
    }

    @Test
    fun fetchRepositories_errorResponseUserAlbum() {
        `when`(this.userInfoRepository.getUserAlbumListMutableLiveData(this.retryableCallback)).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }
        val observer =
            mock(Observer::class.java) as Observer<List<UserAlbumListResponse.AlbumListResponseItem>>
        this.userInfoViewModel.userAlbumListLiveData.observeForever(observer)
        this.retryableCallback.onSuccess(
            UserInfoApplication.applicationContext().resources.getString(
                R.string.fail
            )
        )
        assertNotNull(this.userInfoViewModel.userAlbumListLiveData.value)
    }

}