package com.setianjay.languageandframeworkapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.setianjay.languageandframeworkapps.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val intentPoster by lazy { intent.getIntExtra("poster",0) }
    private val intentTitle by lazy { intent.getStringExtra("title") }
    private val intentDetail by lazy { intent.getStringExtra("detail") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showDataDetail()
        setupListener()
    }

    private fun showDataDetail(){
        initData()
    }

    private fun initData(){
        binding.ivPoster.setImageResource(intentPoster)
        binding.tvTitle.text = intentTitle
        binding.tvDetail.text = intentDetail
    }

    private fun setupListener(){
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}