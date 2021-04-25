package com.example.colan_infotech.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colan_infotech.MainActivity
import com.example.colan_infotech.R
import com.example.colan_infotech.database.DatabaseHelper
import com.example.colan_infotech.databinding.FragmentProductDetailBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ProductDetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var db: DatabaseHelper =DatabaseHelper(MainActivity.getIntanse())
    lateinit var binding: FragmentProductDetailBinding
    val comments: ArrayList<String> = ArrayList()
    private var commentsAdapter:CommentsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val name = arguments!!.getString("name")
        val productid = arguments!!.getString("productid")
        val title = arguments!!.getString("title")

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_product_detail, container, false);
        binding.productName.text=name
        binding.titlePublic.text=title
        var values =db.allAuth
        for (i in 0 until db.count){
            if (productid.equals(values.productid.get(i))){
                comments!!.add(values.comment.get(i))
            }
        }
        if (comments!=null&&comments.size>0){
            commentsAdapter = CommentsAdapter(MainActivity.getIntanse(),comments)
            val mGridLayoutManager = GridLayoutManager(MainActivity.getIntanse(), 1)
            mGridLayoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.rvComments.setLayoutManager(mGridLayoutManager)
            binding.rvComments.setNestedScrollingEnabled(false)
            binding.rvComments.setAdapter(commentsAdapter)
        }

        return binding.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}