package com.techmahidra.optustest.ui.userinfo.viewmodel

import androidx.lifecycle.Observer
import com.techmahidra.optustest.data.repository.UserInfoRepository
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.data.response.UserInfoListResponse
import io.reactivex.Maybe
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

    private lateinit var userInfoRepository: UserInfoRepository
    private lateinit var userInfoViewModel: UserInfoViewModel
    private lateinit var retryableCallback: UserInfoRepository.RetryableCallback
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.userInfoRepository = UserInfoRepository()
        this.userInfoViewModel = UserInfoViewModel()
    }

    @Test
    fun fetchRepositories_positiveResponseUserInfo() {
        `when`(this.userInfoRepository.getUserInfoListMutableLiveData(retryableCallback)).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }

        val observer = mock(Observer::class.java) as Observer<List<UserInfoListResponse.UserInfoListResponseItem>>
        this.userInfoViewModel.userInfoListLiveData.observeForever(observer)


        assertNotNull(this.userInfoViewModel.userInfoListLiveData.value)
    }

    @Test
    fun fetchRepositories_positiveResponseUserAlbum() {
        `when`(this.userInfoRepository.getUserAlbumListMutableLiveData(retryableCallback)).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<UserInfoRepository>())
        }

        val observer = mock(Observer::class.java) as Observer<List<UserAlbumListResponse.AlbumListResponseItem>>
        this.userInfoViewModel.userAlbumListLiveData.observeForever(observer)


        assertNotNull(this.userInfoViewModel.userAlbumListLiveData.value)
    }



}