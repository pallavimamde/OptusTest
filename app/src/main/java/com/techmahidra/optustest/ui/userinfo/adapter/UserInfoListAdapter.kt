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

class UserInfoListAdapter(private val userInfoList: ArrayList<UserInfoListResponse.UserInfoListResponseItem>) :
    RecyclerView.Adapter<UserInfoListAdapter.ViewHolder>() {
    var rowIndex = -1 // default selected row index

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        /*  val v = LayoutInflater.from(parent.context)
              .inflate(R.layout.adapter_user_info_list, parent, false)*/
        val binding: AdapterUserInfoListBinding = DataBindingUtil.inflate(
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
        private val rowEmpLayout = binding.constraintLayUserInfo
        /*  private val rowUserName = binding.textViewUserName
          private val rowUserEmail = binding.textViewUserEmail
          private val rowUserPhone = binding.textViewUserPhone
          private val rowUserId = binding.textViewUserId*/

        // bind data to view change bg color of selected item
        fun bind(userInfo: UserInfoListResponse.UserInfoListResponseItem, position: Int) {

            binding.setVariable(
                BR.userInfoModel, userInfo
            )
            binding.userInfoModel = userInfo
            binding.executePendingBindings()
            /*  rowUserName.text = UserInfoApplication.applicationContext().resources.getString(R.string.name)+userInfo.name
              rowUserEmail.text = UserInfoApplication.applicationContext().resources.getString(R.string.email)+userInfo.email
              rowUserPhone.text = UserInfoApplication.applicationContext().resources.getString(R.string.phone)+userInfo.phone
              rowUserId.text = UserInfoApplication.applicationContext().resources.getString(R.string.id)+userInfo.id.toString()*/

            if (rowIndex === position) {
                rowEmpLayout.setBackgroundColor(
                    UserInfoApplication.applicationContext().resources.getColor(
                        R.color.colorDarkBlue
                    )
                )
            } else {
                rowEmpLayout.setBackgroundColor(
                    UserInfoApplication.applicationContext().resources.getColor(
                        R.color.colorBlue
                    )
                )

            }
            itemView.setOnClickListener {
                rowIndex = position
                notifyDataSetChanged() // notify when data change
            }
        }
    }


}
