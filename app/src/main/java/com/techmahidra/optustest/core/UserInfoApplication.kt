package com.techmahidra.optustest.core

import android.app.Application
import android.content.Context

class UserInfoApplication :Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: UserInfoApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}
