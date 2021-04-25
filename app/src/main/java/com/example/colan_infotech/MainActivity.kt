package com.example.colan_infotech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.colan_infotech.databinding.ActivityMainBinding
import com.example.colan_infotech.fragments.DashboardFragment
import com.example.colan_infotech.fragments.HomeFragment
import com.example.colan_infotech.fragments.NotificationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    companion object {

        lateinit var instance: MainActivity
        fun getIntanse(): MainActivity {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        instance = this
        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
        }
         val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    Log.d("shilpa","yes")
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .addToBackStack("home")
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.dashboard -> {
                    Log.d("shilpa","yes")
                    val fragment = DashboardFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .addToBackStack("dashboard")
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.notification -> {
                    Log.d("shilpa","yes")
                    val fragment = NotificationFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .addToBackStack("notification")
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        binding.mainBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

}