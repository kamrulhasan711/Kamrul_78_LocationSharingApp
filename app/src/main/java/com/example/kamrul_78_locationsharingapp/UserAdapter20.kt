package com.example.kamrul_78_locationsharingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kamrul_78_locationsharingapp.databinding.ItemUser20Binding




class UserAdapter20(private var userList: List<User20>): RecyclerView.Adapter<UserAdapter20.UserViewHolder>() {
    class UserViewHolder(private val binding: ItemUser20Binding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User20){
            binding.apply {
                displayNameTxt.text = user.displayname
                emailTxt.text = user.email
                locationTxt.text = user.location
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUser20Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.bind(user)

    }
    fun updateData(newList: List<User20>) {
        userList = newList
        notifyDataSetChanged()
    }
}