package com.example.jobsearch.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobsearch.input.databinding.FragmentLogAccountBinding

class LogAccountFragment : Fragment() {
    private var _binding: FragmentLogAccountBinding? = null
    private val binding: FragmentLogAccountBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogAccountBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}