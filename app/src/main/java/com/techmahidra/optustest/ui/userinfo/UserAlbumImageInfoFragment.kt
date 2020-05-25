package com.techmahidra.optustest.ui.userinfo

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.techmahidra.optustest.R
import com.techmahidra.optustest.data.response.UserImageInfo
import com.techmahidra.optustest.databinding.FragmentImageInfoBinding
import kotlinx.android.synthetic.main.fragment_user_info.*


/**
 * A simple [Fragment] subclass.
 * Use the [ImageInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserAlbumImageInfoFragment : Fragment() {

    private var isRefreshing = false

    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userImageInfo : UserImageInfo = this.arguments?.getSerializable("image_info") as UserImageInfo
        val binding : FragmentImageInfoBinding  = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_image_info,
            container,
            false
        )
        binding.imageInfo = userImageInfo
        binding.executePendingBindings()
        return binding.getRoot()
    }

    // on fragment view create initialize the params
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        swipeRefreshLayout.setOnRefreshListener {
            isRefreshing = true
            loadData()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun loadData() {

    }

}


