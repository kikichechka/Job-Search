package com.example.jobsearch.input

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edit.setCompoundDrawablesRelativeWithIntrinsicBounds(
            resources.getDrawable(R.drawable.letter, null), null, null, null
        )

        binding.edit.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edit.hint = null
                binding.layoutEdit.hint = null
                binding.edit.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
            }
        }

        binding.edit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.buttonContinue.isEnabled = !p0.isNullOrBlank()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}