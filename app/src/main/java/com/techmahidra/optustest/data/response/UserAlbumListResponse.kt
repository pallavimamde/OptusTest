package com.techmahidra.optustest.data.response

/*
* UserAlbumListResponse - User album list of server response type
* */
class UserAlbumListResponse : ArrayList<UserAlbumListResponse.AlbumListResponseItem>() {
    data class AlbumListResponseItem(
        val albumId: Int, // 100
        val id: Int, // 5000
        var thumbnailUrl: String, // https://via.placeholder.com/150/6dd9cb
        var title: String, // error quasi sunt cupiditate voluptate ea odit beatae
        var url: String // https://via.placeholder.com/600/6dd9cb
    )
}