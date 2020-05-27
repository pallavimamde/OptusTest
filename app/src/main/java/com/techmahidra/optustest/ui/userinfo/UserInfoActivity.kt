package com.techmahidra.optustest.ui.userinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.techmahidra.optustest.R
import com.techmahidra.optustest.data.response.UserImageInfo
import com.techmahidra.optustest.utils.FragmentTransUtil.addFragment
import com.techmahidra.optustest.utils.FragmentTransUtil.replaceFragment

/*
* Launching activity
* */
class UserInfoActivity : AppCompatActivity() {
    companion object {
        var selectedUserId = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        addUserFragment(savedInstanceState)
    }

    // add fragment
    private fun addUserFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            addFragment(UserInfoListFragment(), R.id.frameLayoutContainer) // add fragment
    }

    // replace fragment
    fun replaceUserFragment(fragment: Fragment) {
        replaceFragment(fragment, R.id.frameLayoutContainer)
    }

    // replace fragment with pass data
    fun replaceUserFragment(fragment: Fragment, imgInfo: UserImageInfo) {
        replaceFragment(fragment, R.id.frameLayoutContainer, imgInfo)
    }
}
