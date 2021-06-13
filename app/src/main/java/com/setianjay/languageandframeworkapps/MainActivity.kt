package com.setianjay.languageandframeworkapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
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
        binding.ivMenus.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.iv_profile -> {
                showProfile()
            }
            R.id.iv_menus -> {
                showPopupMenu()
            }
        }
    }

    private fun showProfile(){
        Intent(applicationContext,ProfileActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun showPopupMenu(){
        val popupMenu = PopupMenu(this,binding.ivMenus)
        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
            when(item!!.itemId){
                R.id.fav_menu_languages -> {
                    Toast.makeText(this@MainActivity, "Favorite Languages", Toast.LENGTH_SHORT).show()
                }
                R.id.fav_menu_frameworks -> {
                    Toast.makeText(this@MainActivity, "Favorite Frameworks", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        popupMenu.show()
    }
}