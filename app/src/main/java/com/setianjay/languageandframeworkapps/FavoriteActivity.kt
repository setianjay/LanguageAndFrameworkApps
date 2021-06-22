package com.setianjay.languageandframeworkapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.languageandframeworkapps.constant.Constants
import com.setianjay.languageandframeworkapps.database.DatabaseBuilder
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity
import com.setianjay.languageandframeworkapps.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFavoriteBinding.inflate(layoutInflater) }
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var viewModel: ContentViewModel
    private lateinit var intentType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        setupViewModel()
        setupRecycleView()
        setupObserver()
        setupListener()
    }

    private fun initData() {
        intentType = intent.getStringExtra(Constants.EXTRA_TYPE) ?: "Not exists"
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ContentViewModelFactory(
                ContentRepository(DatabaseBuilder.getDatabase(this@FavoriteActivity))
            )
        ).get(ContentViewModel::class.java)
    }

    private fun setupRecycleView() {
        favoriteAdapter =
            FavoriteAdapter(arrayListOf(), object : FavoriteAdapter.OnAdapterListener {
                override fun onClick(data: ContentEntity) {
                    Intent(this@FavoriteActivity, DetailActivity::class.java).also {
                        it.putExtra(Constants.EXTRA_POSTER, data.poster)
                        it.putExtra(Constants.EXTRA_TITLE, data.title)
                        it.putExtra(Constants.EXTRA_DETAIL, data.detail)
                        it.putExtra(Constants.EXTRA_TYPE, intentType)
                        startActivity(it)
                    }
                }

                override fun onLongClick(data: ContentEntity) {
                    val dialog = AlertDialog.Builder(this@FavoriteActivity)
                        .setTitle("Delete Content")
                        .setMessage("Are you sure want delete ${data.title}?")
                        .setPositiveButton("Delete") { _, _ ->
                            viewModel.delete(data)
                            finish()
                            overridePendingTransition(0,0)
                            startActivity(intent)
                            overridePendingTransition(0,0)
                        }
                        .setNegativeButton("Cancel") { _, _ ->
                            Toast.makeText(
                                this@FavoriteActivity,
                                "You cancel delete ${data.title} from favorite",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    dialog.show()
                }
            })

        binding.rvFavorite.apply {
            adapter = favoriteAdapter
            layoutManager =
                LinearLayoutManager(this@FavoriteActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun setupObserver() {
        viewModel.getContent(intentType).observe(this@FavoriteActivity, Observer {
            favoriteAdapter.setData(it)
        })
    }

    private fun setupListener() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.etSearch.doAfterTextChanged {
            favoriteAdapter.filter.filter(it.toString())
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}