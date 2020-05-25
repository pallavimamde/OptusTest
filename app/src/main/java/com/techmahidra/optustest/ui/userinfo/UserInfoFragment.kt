package com.techmahidra.optustest.ui.userinfo

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.techmahidra.optustest.R
import com.techmahidra.optustest.data.response.UserInfoListResponse
import com.techmahidra.optustest.ui.userinfo.adapter.UserInfoListAdapter
import com.techmahidra.optustest.ui.userinfo.viewmodel.UserInfoViewModel
import kotlinx.android.synthetic.main.fragment_user_info.*
import kotlinx.android.synthetic.main.no_data_layout.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserInfoFragment : Fragment() {

    private var userInfoListAdapter: RecyclerView.Adapter<UserInfoListAdapter.ViewHolder>? = null
    private lateinit var loadingDialog: Dialog
    private var isRefreshing = false

    companion object {
        val modifiedUserList: ArrayList<UserInfoListResponse.UserInfoListResponseItem> = ArrayList()
    }

    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    // on fragment view create initialize the params
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        loadData()
        swipeRefreshLayout.setOnRefreshListener {
            isRefreshing = true
            loadData(isRefreshing)
            swipeRefreshLayout.isRefreshing = false
        }

    }


    private fun loadData(isRefreshing: Boolean = false) {

                    updateUI()
    }

    // update Userinfo list UI
    @SuppressLint("WrongConstant")
    fun updateUI() {
            modifiedUserList.clear()

            // initialize the @UserInfoListAdapter and set list
            recyclerViewUserInfoList.layoutManager =
                LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            userInfoListAdapter = UserInfoListAdapter(modifiedUserList)
            recyclerViewUserInfoList.adapter = userInfoListAdapter

    }


}
