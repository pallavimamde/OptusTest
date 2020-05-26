package com.techmahidra.optustest.utils

import com.techmahidra.optustest.data.response.UserImageInfo

/*
* UserActionListener - Listen recyclerview item click action
* */
interface UserActionListener {
    fun onClickAction()
    fun onClickAction(imageInfo : UserImageInfo)
}