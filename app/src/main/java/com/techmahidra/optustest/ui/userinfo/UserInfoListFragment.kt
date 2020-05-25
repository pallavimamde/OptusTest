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
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.techmahidra.optustest.R
import com.techmahidra.optustest.core.UserInfoApplication
import com.techmahidra.optustest.data.response.UserImageInfo
import com.techmahidra.optustest.data.response.UserInfoListResponse
import com.techmahidra.optustest.ui.userinfo.adapter.UserInfoListAdapter
import com.techmahidra.optustest.ui.userinfo.viewmodel.UserInfoViewModel
import com.techmahidra.optustest.utils.NetworkConnectionStatus
import com.techmahidra.optustest.utils.UserActionListener
import kotlinx.android.synthetic.main.fragment_user_info.*
import kotlinx.android.synthetic.main.no_data_layout.*


/**
 * A simple [Fragment] subclass.
 * Use the [UserInfoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserInfoListFragment : Fragment(), UserActionListener {


    private var userInfoViewModel: UserInfoViewModel? = null
    private var userInfoListAdapter: RecyclerView.Adapter<UserInfoListAdapter.ViewHolder>? = null
    private lateinit var loadingDialog: Dialog
    private var isRefreshing = false
    private val modifiedUserList: ArrayList<UserInfoListResponse.UserInfoListResponseItem> = ArrayList()
    private var actionBar: ActionBar? = null


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
        actionBar = (activity as UserInfoActivity).supportActionBar
        actionBar?.title = UserInfoApplication.applicationContext().resources.getString(R.string.user_info)
        showData()
        activity?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        loadingDialog = Dialog(activity as AppCompatActivity)
        loadingDialog.setCancelable(false)
        loadingDialog.setCanceledOnTouchOutside(false)
        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
        loadData()
        swipeRefreshLayout.setOnRefreshListener {
            isRefreshing = true
            loadData(isRefreshing)
            swipeRefreshLayout.isRefreshing = false
        }

    }


    // get data from server
    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadData(isRefreshing: Boolean = false) {
        // check internet connection
        val hasInternetConnected =
            NetworkConnectionStatus(UserInfoApplication.applicationContext()).isOnline()
        if (hasInternetConnected) {
            if (!isRefreshing) { // if default refreshing is visible don't show loading dialog
                showLoading(
                    UserInfoApplication.applicationContext().resources.getString(
                        R.string.please_wait
                    )
                )
            }
            // check the observer when api response is success and update list
            userInfoViewModel?.userInfoListLiveData?.observe(
                viewLifecycleOwner, Observer { userInfoListResponse ->
                    updateUI(userInfoListResponse)
                    hideLoading()
                })

        } else {
            showError(UserInfoApplication.applicationContext().resources.getString(R.string.network_error))
        }
    }

    // update UI
    @SuppressLint("WrongConstant")
    fun updateUI(response: List<UserInfoListResponse.UserInfoListResponseItem>) {
        if (response.isNotEmpty()) {

            modifiedUserList.clear()
            for (item in response) {
                var isAllNull = false

                //check the empty or null string in response object
                if (item.name.isNullOrBlank() && item.email.isNullOrBlank() && item.phone.isNullOrBlank()) {
                    isAllNull = true
                } else {
                    if (item.name.isNullOrBlank()) {
                        item.name = "Empty String"
                    }
                    if (item.email.isNullOrBlank()) {
                        item.email = "Empty Value"
                    }
                    if (item.phone.isNullOrBlank()) {
                        item.phone = "Empty Value"
                    }

                }
                if (!isAllNull) {
                    modifiedUserList.add(item)
                }
            }
            // initialize the @UserInfoListAdapter and set list
            recyclerViewUserInfoList.layoutManager =
                LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            userInfoListAdapter = UserInfoListAdapter(this,modifiedUserList)
            recyclerViewUserInfoList.adapter = userInfoListAdapter

        } else {
            showNoData()
        }
    }


    // if there will be any error occurred while server call
    fun showError(errorMsg: String) {
        Toast.makeText(UserInfoApplication.applicationContext(), errorMsg, Toast.LENGTH_SHORT)
            .show()
    }

    // show dialog while loading data from server
    fun showLoading(loadingMessage: String) {
        /*  loadingDialog.setContentView(R.layout.progress_bar)
          loadingDialog.show()*/

    }

    // hide loading
    fun hideLoading() {
        //loadingDialog.dismiss()
    }

    // if there is empty list then so no data layout
    fun showNoData() {
        recyclerViewUserInfoList.visibility = View.GONE
        textViewNoData.visibility = View.VISIBLE
    }

    // show userinfo list if list available else show no data view
    fun showData() {
        recyclerViewUserInfoList.visibility = View.VISIBLE
        textViewNoData.visibility = View.GONE
    }

    override fun onClickAction() {
        (activity as UserInfoActivity).replaceUserFragment(UserAlbumListFragment())
    }

    override fun onClickAction(imageInfo: UserImageInfo) {

    }

}