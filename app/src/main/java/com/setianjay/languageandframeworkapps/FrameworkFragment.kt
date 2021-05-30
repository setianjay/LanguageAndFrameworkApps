package com.setianjay.languageandframeworkapps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
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