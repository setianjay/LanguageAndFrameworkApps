package com.setianjay.languageandframeworkapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.setianjay.languageandframeworkapps.databinding.FragmentLanguageBinding


class LanguageFragment : Fragment() {
    private lateinit var binding: FragmentLanguageBinding
    private lateinit var languageAdapter: LanguageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
    }

    override fun onStart() {
        super.onStart()
        showLanguage(LanguageAndFrameworkData.languageList)
    }

    private fun setupRecycleView() {
        languageAdapter =
            LanguageAdapter(arrayListOf(), object : LanguageAdapter.OnAdapterListener {
                override fun onClick(data: LanguageAndFrameworkModel) {
                    Intent(requireContext(), DetailActivity::class.java).also {
                        it.putExtra("poster", data.poster)
                        it.putExtra("title", data.title)
                        it.putExtra("detail", data.detail)
                        it.putExtra("type", "languages")
                        startActivity(it)
                    }
                }


            })

        binding.rvLanguages.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = languageAdapter
            setHasFixedSize(true)
        }
    }

    private fun showLanguage(data: MutableList<LanguageAndFrameworkModel>) {
        languageAdapter.setData(data)
    }
}