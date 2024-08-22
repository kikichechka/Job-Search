package com.example.jobsearch.input

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobsearch.input.databinding.FragmentCheckingAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckingAccountFragment : Fragment() {
    private var email: String? = null
    private var _binding: FragmentCheckingAccountBinding? = null
    private val binding: FragmentCheckingAccountBinding
        get() = _binding!!
    interface Callback {
        fun click(str: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString(KEY_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckingAccountBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textSentCode.text = "${binding.textSentCode.text} $email"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val KEY_ARG = "KEY_ARG_CHECKING"
    }
}