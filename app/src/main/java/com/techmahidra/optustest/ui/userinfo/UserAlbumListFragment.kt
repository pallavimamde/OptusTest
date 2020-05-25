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
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.ui.userinfo.UserInfoActivity.Companion.selectedUserId
import com.techmahidra.optustest.ui.userinfo.adapter.UserAlbumListAdapter
import com.techmahidra.optustest.ui.userinfo.viewmodel.UserInfoViewModel
import com.techmahidra.optustest.utils.NetworkConnectionStatus
import com.techmahidra.optustest.utils.UserActionListener
import kotlinx.android.synthetic.main.fragment_album_list.*
import kotlinx.android.synthetic.main.fragment_user_info.swipeRefreshLayout
import kotlinx.android.synthetic.main.no_data_layout.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserAlbumListFragment : Fragment(), UserActionListener {

    private var userInfoViewModel: UserInfoViewModel? = null
    private var userAlbumListAdapter: RecyclerView.Adapter<UserAlbumListAdapter.ViewHolder>? = null
    private lateinit var loadingDialog: Dialog
    private var isRefreshing = false
    private val modifiedUserAlbumList: ArrayList<UserAlbumListResponse.AlbumListResponseItem> = ArrayList()
    private var actionBar: ActionBar? = null


    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album_list, container, false)
    }

    // on fragment view create initialize the params
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar = (activity as UserInfoActivity).supportActionBar
        actionBar?.title = UserInfoApplication.applicationContext().resources.getString(R.string.album_id) + selectedUserId
        showData()
        activity?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        loadingDialog = Dialog(activity as AppCompatActivity)
        loadingDialog.setCancelable(false)
        loadingDialog.setCanceledOnTouchOutside(false)
        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
        loadUserAlbumData()
        swipeRefreshLayout.setOnRefreshListener {
            isRefreshing = true
            loadUserAlbumData(isRefreshing)
            swipeRefreshLayout.isRefreshing = false
        }

    }


    // get data from server
    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadUserAlbumData(isRefreshing: Boolean = false) {
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
            userInfoViewModel?.userAlbumListLiveData?.observe(
                viewLifecycleOwner, Observer { userAlbumListResponse ->
                    updateUI(userAlbumListResponse)
                    hideLoading()
                })

        } else {
            showError(UserInfoApplication.applicationContext().resources.getString(R.string.network_error))
        }
    }

    // update UI
    @SuppressLint("WrongConstant")
    fun updateUI(response: List<UserAlbumListResponse.AlbumListResponseItem>) {
        if (response.isNotEmpty()) {

            modifiedUserAlbumList.clear()
            for (item in response) {
                var isAllNull = false

                //check the empty or null string in response object
                if (item.title.isNullOrBlank() && item.thumbnailUrl.isNullOrBlank()) {
                    isAllNull = true
                } else {
                    if (item.title.isNullOrBlank()) {
                        item.title = "Empty String"
                    }
                    if (item.thumbnailUrl.isNullOrBlank()) {
                        item.thumbnailUrl = "Empty Value"
                    }
                }
                if (!isAllNull) {
                    modifiedUserAlbumList.add(item)
                }
            }
            // initialize the @UserAlbumListAdapter and set list
            recyclerViewUserAlbumList.layoutManager =
                LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            userAlbumListAdapter = UserAlbumListAdapter(this, modifiedUserAlbumList)
            recyclerViewUserAlbumList.adapter = userAlbumListAdapter
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
        loadingDialog.setContentView(R.layout.progress_bar)
        loadingDialog.show()

    }

    // hide loading
    fun hideLoading() {
        loadingDialog.dismiss()
    }

    // if there is empty list then so no data layout
    fun showNoData() {
        recyclerViewUserAlbumList.visibility = View.GONE
        textViewNoData.visibility = View.VISIBLE
    }

    // show employee list if list available else show no data view
    fun showData() {
        recyclerViewUserAlbumList.visibility = View.VISIBLE
        textViewNoData.visibility = View.GONE
    }

    override fun onClickAction() {

    }

    override fun onClickAction(imageInfo : UserImageInfo) {
        (activity as UserInfoActivity).replaceUserFragment(UserAlbumImageInfoFragment(), imageInfo)
    }

}
