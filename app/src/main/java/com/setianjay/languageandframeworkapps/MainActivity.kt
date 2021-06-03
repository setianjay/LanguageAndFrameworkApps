package com.setianjay.languageandframeworkapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import com.setianjay.languageandframeworkapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTabLayout()
        setupListener()
    }

    private fun setupTabLayout(){
        val tabAdapter = HomeTabLayoutAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = tabAdapter

        val titlesTabLayout = listOf("Language","Framework")
        TabLayoutMediator(binding.tabLayout,binding.viewPager){
            tab, position -> tab.text = titlesTabLayout[position]
        }.attach()
    }

    private fun setupListener(){
        binding.ivProfile.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.iv_profile -> {
                Intent(applicationContext,ProfileActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}