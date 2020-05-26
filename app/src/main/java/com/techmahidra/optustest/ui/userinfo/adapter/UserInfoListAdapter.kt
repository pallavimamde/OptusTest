package com.techmahidra.optustest.ui.userinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techmahidra.optustest.BR
import com.techmahidra.optustest.R
import com.techmahidra.optustest.core.UserInfoApplication
import com.techmahidra.optustest.data.response.UserInfoListResponse
import com.techmahidra.optustest.databinding.AdapterUserInfoListBinding
import com.techmahidra.optustest.ui.userinfo.UserInfoActivity.Companion.selectedUserId
import com.techmahidra.optustest.utils.UserActionListener

class UserInfoListAdapter(
    private val userActionListener: UserActionListener,
    private val userInfoList: ArrayList<UserInfoListResponse.UserInfoListResponseItem>
) :
    RecyclerView.Adapter<UserInfoListAdapter.ViewHolder>() {
    private lateinit var binding: AdapterUserInfoListBinding

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_user_info_list,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    //get list item count
    override fun getItemCount() = userInfoList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(userInfoList[position], position)
    }

    inner class ViewHolder(private val binding: AdapterUserInfoListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val rowUserInfoLayout = binding.constraintLayUserInfo

        // bind data to view change bg color of selected item
        fun bind(userInfo: UserInfoListResponse.UserInfoListResponseItem, position: Int) {
            binding.setVariable(
                BR.userInfoModel, userInfo
            )
            binding.userInfoModel = userInfo
            binding.executePendingBindings()

            itemView.setOnClickListener {
                selectedUserId = userInfoList[position].id
                notifyDataSetChanged() // notify when data change
                userActionListener.onClickAction()
            }
        }
    }

}
