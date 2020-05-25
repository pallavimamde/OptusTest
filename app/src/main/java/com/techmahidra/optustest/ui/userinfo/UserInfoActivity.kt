package com.techmahidra.optustest.ui.userinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techmahidra.optustest.R
import com.techmahidra.optustest.utils.FragmentTransUtil.addFragment

/*
* Launching activity
* */
class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        addUserFragment(savedInstanceState)
    }

    private fun addUserFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            addFragment(UserInfoFragment(), R.id.frameLayoutContainer) // add fragment
    }
}
