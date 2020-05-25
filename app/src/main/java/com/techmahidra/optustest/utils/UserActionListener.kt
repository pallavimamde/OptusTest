package com.techmahidra.optustest.utils

import com.techmahidra.optustest.data.response.UserImageInfo

interface UserActionListener {
    fun onClickAction()
    fun onClickAction(imageInfo : UserImageInfo)
}