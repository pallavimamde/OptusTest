package com.techmahidra.optustest.ui.userinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techmahidra.optustest.R
import com.techmahidra.optustest.BR
import com.techmahidra.optustest.core.UserInfoApplication
import com.techmahidra.optustest.data.response.UserImageInfo
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.databinding.AdapterAlbumListBinding
import com.techmahidra.optustest.utils.UserActionListener

class UserAlbumListAdapter(
    private val userActionListener: UserActionListener,
    private val userAlbumList: ArrayList<UserAlbumListResponse.AlbumListResponseItem>) :
    RecyclerView.Adapter<UserAlbumListAdapter.ViewHolder>() {
    var rowIndex = -1 // default selected row index

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val binding: AdapterAlbumListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_album_list,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    //get list item count
    override fun getItemCount() = userAlbumList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(userAlbumList[position], position)
    }

    inner class ViewHolder(private val binding: AdapterAlbumListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val rowUserAlbumLayout = binding.constraintLayUserAlbum

        // bind data to view change bg color of selected item
        fun bind(userAlbum: UserAlbumListResponse.AlbumListResponseItem, position: Int) {
            binding.setVariable(
                BR.userAlbumModel, userAlbum
            )
            binding.userAlbumModel = userAlbum
            binding.executePendingBindings()
            if (rowIndex === position) {
                rowUserAlbumLayout.setBackgroundColor(
                    UserInfoApplication.applicationContext().resources.getColor(
                        R.color.colorDarkBlue
                    )
                )
            } else {
                rowUserAlbumLayout.setBackgroundColor(
                    UserInfoApplication.applicationContext().resources.getColor(
                        R.color.colorBlue
                    )
                )

            }
            itemView.setOnClickListener {
                rowIndex = position
                notifyDataSetChanged() // notify when data change
                userActionListener.onClickAction(UserImageInfo(userAlbumList[position].url,userAlbumList[position].title))
            }
        }
    }


}
