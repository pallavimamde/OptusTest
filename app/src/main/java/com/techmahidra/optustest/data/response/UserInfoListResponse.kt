package com.techmahidra.optustest.data.response

import com.google.gson.annotations.SerializedName

/*
* UserInfoListResponse - Userinfo list in the format of server response type */
class UserInfoListResponse : ArrayList<UserInfoListResponse.UserInfoListResponseItem>() {
    data class UserInfoListResponseItem(
        val address: Address,
        val company: Company,
        var email: String, // Rey.Padberg@karina.biz
        val id: Int, // 10
        var name: String, // Clementina DuBuque
        var phone: String, // 024-648-3804
        val username: String, // Moriah.Stanton
        val website: String // ambrose.net
    ) {
        data class Address(
            val city: String, // Lebsackbury
            val geo: Geo,
            val street: String, // Kattie Turnpike
            val suite: String, // Suite 198
            val zipcode: String // 31428-2261
        ) {
            data class Geo(
                val lat: String, // -38.2386
                val lng: String // 57.2232
            )
        }

        data class Company(
            val bs: String, // target end-to-end models
            val catchPhrase: String, // Centralized empowering task-force
            val name: String // Hoeger LLC
        )
    }
}

