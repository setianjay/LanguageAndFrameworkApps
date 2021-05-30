package com.setianjay.languageandframeworkapps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setianjay.languageandframeworkapps.databinding.FragmentFrameworkBinding

class FrameworkFragment : Fragment() {
    private lateinit var binding: FragmentFrameworkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFrameworkBinding.inflate(inflater,container,false)
        return binding.root
    }
}