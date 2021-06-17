package com.setianjay.languageandframeworkapps

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.setianjay.languageandframeworkapps.constant.Constants
import com.setianjay.languageandframeworkapps.databinding.FragmentFrameworkBinding

class FrameworkFragment : Fragment() {
    private lateinit var binding: FragmentFrameworkBinding
    private lateinit var frameworkAdapter: FrameworkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFrameworkBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
    }

    override fun onStart() {
        super.onStart()
        showFramework()
    }

    private fun setupRecycleView(){
        frameworkAdapter = FrameworkAdapter(arrayListOf(),object: FrameworkAdapter.OnAdapterListener{
            override fun onClick(data: LanguageAndFrameworkModel) {
                Intent(requireContext(), DetailActivity::class.java).also {
                    it.putExtra(Constants.EXTRA_POSTER, data.poster)
                    it.putExtra(Constants.EXTRA_TITLE, data.title)
                    it.putExtra(Constants.EXTRA_DETAIL, data.detail)
                    it.putExtra(Constants.EXTRA_TYPE, "frameworks")
                    startActivity(it)
                }
            }

        })

        binding.rvFrameworks.apply {
            adapter = frameworkAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)
        }
    }

    private fun showFramework(){
        frameworkAdapter.setData(LanguageAndFrameworkData.frameworkList)
    }
}