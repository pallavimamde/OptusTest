package com.techmahidra.optustest.data.response

import java.io.Serializable

/*
* UserImageInfo - This model class used to share data from UserAlbum screen to ImageInfo screen*/
data class UserImageInfo(
    var url: String,
    var title: String,
    var photoId: String,
    var albumId: String
) : Serializable