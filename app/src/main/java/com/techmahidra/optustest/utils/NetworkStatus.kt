package com.techmahidra.optustest.utils

/*
* NetworkStatus -  interface to check online status of internet
* */
interface NetworkStatus {
    fun isOnline(): Boolean // to check internet is connected or not
}