package com.techmahidra.optustest.data.response


import com.google.gson.annotations.SerializedName

class AlbumListResponseItem : ArrayList<AlbumListResponseItem.AlbumListResponseItemItem>(){
    data class AlbumListResponseItemItem(
        val albumId: Int, // 100
        val id: Int, // 5000
        val thumbnailUrl: String, // https://via.placeholder.com/150/6dd9cb
        val title: String, // error quasi sunt cupiditate voluptate ea odit beatae
        val url: String // https://via.placeholder.com/600/6dd9cb
    )
}