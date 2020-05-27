package com.techmahidra.optustest.ui.userinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.techmahidra.optustest.R
import com.techmahidra.optustest.BR
import com.techmahidra.optustest.data.response.UserImageInfo
import com.techmahidra.optustest.data.response.UserAlbumListResponse
import com.techmahidra.optustest.databinding.AdapterAlbumListBinding
import com.techmahidra.optustest.utils.UserActionListener
import com.techmahidra.optustest.utils.loadImage

/*
* UserAlbumListAdapter - bind the data to UI component, which is helps to show Userinfo list
* */
class UserAlbumListAdapter(
    private val userActionListener: UserActionListener,
    private val userAlbumList: ArrayList<UserAlbumListResponse.AlbumListResponseItem>
) :
    RecyclerView.Adapter<UserAlbumListAdapter.ViewHolder>() {


    // bind layout to set the data to views
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

    // bind viewholder with data
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(userAlbumList[position], position)
    }

    inner class ViewHolder(private val binding: AdapterAlbumListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val rowUserAlbumLayout = binding.constraintLayUserAlbum

        // onclick of recyclerview item store some data in UserImageInfo, which will display in 3rd screen i.e. ImageInfo
        fun bind(userAlbum: UserAlbumListResponse.AlbumListResponseItem, position: Int) {
            binding.imageViewThumbnail.loadImage(userAlbumList[position].thumbnailUrl)

            binding.setVariable(
                BR.userAlbumModel, userAlbum
            )
            binding.userAlbumModel = userAlbum
            binding.executePendingBindings()

            itemView.setOnClickListener {
                notifyDataSetChanged() // notify when data change
                userActionListener.onClickAction(
                    UserImageInfo(
                        userAlbumList[position].url,
                        userAlbumList[position].title,
                        userAlbumList[position].id.toString(),
                        userAlbumList[position].albumId.toString()
                    )
                )
            }
        }
    }


}
