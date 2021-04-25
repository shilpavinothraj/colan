package com.example.colan_infotech.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.colan_infotech.MainActivity
import com.example.colan_infotech.R
import com.example.colan_infotech.database.DatabaseHelper
import product_response_Base


class ProductListAdapter(val mContext: Context, val products: List<product_response_Base?>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>(){
    lateinit var view: View
    private var db: DatabaseHelper= DatabaseHelper(mContext)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.productlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products?.size!!
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        if (products != null) {
            holder.bindItems(products.get(position), view, position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(objectmenu: product_response_Base?, views: View, position: Int) {
            var productimage = view.findViewById<ImageView>(R.id.image)
            var name = view.findViewById<AppCompatTextView>(R.id.name)
            var subname = view.findViewById<AppCompatTextView>(R.id.title)
            var comment = view.findViewById<AppCompatEditText>(R.id.et_comment)
            var post = view.findViewById<AppCompatButton>(R.id.btn_post)
            var layout = view.findViewById<ConstraintLayout>(R.id.layout)



            post.setOnClickListener(View.OnClickListener {
                if (comment.text.toString().isNotEmpty())
                {
                    try {
                        db = DatabaseHelper(mContext)
                        db!!.insertAuth(objectmenu!!.id,objectmenu!!.name,comment.text.toString(),objectmenu!!.full_name)
                        Toast.makeText(MainActivity.getIntanse(), "Comment was Posted successfully", Toast.LENGTH_SHORT).show()
                        comment.setText("")
                    } catch (e: Exception) {
                    }

                }else{
                    comment?.error = "Please enter comment"

                }

            })
            layout.setOnClickListener(View.OnClickListener {
                  val fragment = ProductDetailFragment()
                val args = Bundle()
                args.putString("name", objectmenu?.name)
                args.putString("productid", objectmenu?.id)
                args.putString("title", objectmenu?.full_name)

                fragment.setArguments(args)
   MainActivity.getIntanse().supportFragmentManager.beginTransaction()
       .replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
       .addToBackStack("productdetail")
       .commit()

            })


           /* if (objectmenu.urlToImage!=null)
            Glide.with(mContext)
                    .load(objectmenu.urlToImage)
                    .placeholder(R.drawable.no_img)
                    .dontAnimate().into(productimage)*/
            name.text= objectmenu!!.name
            subname.text= objectmenu!!.full_name


        }
    }
}