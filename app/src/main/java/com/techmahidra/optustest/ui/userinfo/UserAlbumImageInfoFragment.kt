package com.techmahidra.optustest.ui.userinfo

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.techmahidra.optustest.R
import com.techmahidra.optustest.core.UserInfoApplication
import com.techmahidra.optustest.data.response.UserImageInfo
import com.techmahidra.optustest.databinding.FragmentImageInfoBinding
import com.techmahidra.optustest.utils.loadImage
import kotlinx.android.synthetic.main.fragment_image_info.*
import kotlinx.android.synthetic.main.fragment_user_info.*
import kotlinx.android.synthetic.main.fragment_user_info.swipeRefreshLayout


/**
 * UserAlbumImageInfoFragment - This fragment helps to display image info with set the actionbar title
 */
class UserAlbumImageInfoFragment : Fragment() {

    private var isRefreshing = false
    private var actionBar: ActionBar? = null
    private lateinit var binding : FragmentImageInfoBinding
    private lateinit var userImageInfo : UserImageInfo

    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userImageInfo = this.arguments?.getSerializable("image_info") as UserImageInfo
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_image_info,
            container,
            false
        )

        return binding.getRoot()
    }

    // on fragment view create initialize the params
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar = (activity as UserInfoActivity).supportActionBar
        actionBar?.title = UserInfoApplication.applicationContext().resources.getString(R.string.photo_id) + binding.imageInfo?.photoId

        swipeRefreshLayout.setOnRefreshListener {
            isRefreshing = true
            loadData()
            swipeRefreshLayout.isRefreshing = false
        }

    }
// Load image using Picasso
    private fun loadData() {
        binding.imageInfo = userImageInfo
        binding.imageViewUserImage.loadImage(binding.imageInfo?.url.toString())
        binding.executePendingBindings()

    }

}


