package com.example.colan_infotech.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colan_infotech.MainActivity
import com.example.colan_infotech.R
import com.example.colan_infotech.adapter.ProductListAdapter
import com.example.colan_infotech.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: HomeFragmentBinding
    private var productListAdapter: ProductListAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.home_fragment, container, false);
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        if (isInternetAvailable(activity)){
            binding.progressBar.visibility= View.VISIBLE
            viewModel.productList()
        }else{
            showAlert()
        }

        viewModel.productlistResponsedata.observe(MainActivity.getIntanse(), Observer {
            it?.let {
                binding.progressBar.visibility=View.GONE
                Log.d("apicall","success")

                if (it!=null){
                    productListAdapter = ProductListAdapter(MainActivity.getIntanse(),it)
                    val mGridLayoutManager = GridLayoutManager(MainActivity.getIntanse(), 1)
                    mGridLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    binding.rvProducts.setLayoutManager(mGridLayoutManager)
                    binding.rvProducts.setNestedScrollingEnabled(false)
                    binding.rvProducts.setAdapter(productListAdapter)
                }
            }
        })

    }
    fun isInternetAvailable(activity: Context?): Boolean {
        if (activity != null) {
            val cm =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            if (cm != null && cm.activeNetworkInfo != null) activeNetworkInfo =
                cm.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected
        }
        return false
    }
    fun showAlert(){
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(activity, R.style.AlertDialogCustom)
        builder.setTitle("Network Error")
        builder.setMessage("Please check your internet connection")
            .setPositiveButton("ok",
                DialogInterface.OnClickListener { dialog, which -> // continue with delete
                    dialog.cancel()
                })

            .show()

    }

}