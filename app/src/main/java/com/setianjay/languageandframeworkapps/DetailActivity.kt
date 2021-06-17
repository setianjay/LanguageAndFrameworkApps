package com.setianjay.languageandframeworkapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.setianjay.languageandframeworkapps.constant.Constants
import com.setianjay.languageandframeworkapps.database.DatabaseBuilder
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity
import com.setianjay.languageandframeworkapps.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val intentPoster by lazy { intent.getIntExtra(Constants.EXTRA_POSTER,0) }
    private val intentTitle by lazy { intent.getStringExtra(Constants.EXTRA_TITLE) }
    private val intentDetail by lazy { intent.getStringExtra(Constants.EXTRA_DETAIL) }
    private val intentType by lazy { intent.getStringExtra(Constants.EXTRA_TYPE) }
    private lateinit var viewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewModel()
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
        binding.ivBack.setOnClickListener(this)
        binding.ivLove.setOnClickListener(this)
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(
            this,
            ContentViewModelFactory(
               ContentRepository(DatabaseBuilder.getDatabase(applicationContext))
            )
        ).get(ContentViewModel::class.java)
    }
    
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_back -> {
                onBackPressed()
            }
            R.id.iv_love -> {
                viewModel.insert(
                    ContentEntity(
                        title = intentTitle!!,
                        poster = intentPoster,
                        detail = intentDetail!!,
                        type = intentType!!
                    )
                )
                Toast.makeText(applicationContext, "You add $intentType $intentTitle to favorite", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
