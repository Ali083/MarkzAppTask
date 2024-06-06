package com.example.markzapptask.aj.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.markzapptask.R
import com.example.markzapptask.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initExitDialog()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)

    }
    var dialog: BottomSheetDialog? = null

    private fun initExitDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_exit, null)
        dialog = BottomSheetDialog(this)
        dialog!!.setContentView(dialogView)

        val exitButton = dialogView.findViewById<TextView>(R.id.exitButton)
        exitButton.setOnClickListener {
            dialog!!.dismiss()
            finishAffinity()
        }
    }

    override fun onBackPressed() {
        dialog?.show()
    }

}