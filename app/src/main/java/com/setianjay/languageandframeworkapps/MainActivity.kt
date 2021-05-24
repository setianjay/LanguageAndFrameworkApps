package com.setianjay.languageandframeworkapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.setianjay.languageandframeworkapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTabLayout()
    }

    private fun setupTabLayout(){
        val tabAdapter = HomeTabLayoutAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = tabAdapter

        val titlesTabLayout = listOf("Language","Framework")
        TabLayoutMediator(binding.tabLayout,binding.viewPager){
            tab, position -> tab.text = titlesTabLayout[position]
        }.attach()
    }
}