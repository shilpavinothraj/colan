package com.example.colan_infotech.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.colan_infotech.MainActivity
import com.example.colan_infotech.R
import com.example.colan_infotech.database.DatabaseHelper

class CommentsAdapter(val mContext: Context, val comments: List<String?>) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>(){
    lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.commentadapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments?.size!!
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(comments.get(position),view,position)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(objectmenu: String?, views: View, position: Int) {
            var comment = view.findViewById<AppCompatTextView>(R.id.comments)
            comment.text= objectmenu


        }
    }

}