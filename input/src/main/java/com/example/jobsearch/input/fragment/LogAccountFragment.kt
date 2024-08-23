package com.example.jobsearch.input.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jobsearch.input.CallbackLog
import com.example.jobsearch.input.R
import com.example.jobsearch.input.databinding.FragmentLogAccountBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LogAccountFragment : Fragment() {

    private var email = ""
    private var _binding: FragmentLogAccountBinding? = null
    private val binding: FragmentLogAccountBinding
        get() = _binding!!

    private lateinit var callbackLog: CallbackLog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogAccountBinding.inflate(inflater)
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            callbackLog = context as CallbackLog
        } catch (e: ClassCastException) {
            throw ClassCastException(e.message)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editRequestFocus()
        setCompoundDrawablesEdit()
        editOnFocusChangeListener()
        editTextChangedListener()
        buttonContinueClickListener()
    }

    private fun editRequestFocus() {
        if (email.isNotEmpty()) {
            binding.edit.requestFocus()
        }
    }

    private fun buttonContinueClickListener() {
        binding.buttonContinue.setOnClickListener {
            if (checkEmail()) {
                callbackLog.clickButtonContinue(email)
            } else
                showError()
        }
    }

    private fun editTextChangedListener() {
        binding.edit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.buttonContinue.isEnabled = !p0.isNullOrBlank()
                email = p0.toString()
                changeButton()
                hideError()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun editOnFocusChangeListener() {
        binding.edit.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edit.hint = null
                binding.layoutEdit.hint = null
                binding.edit.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setCompoundDrawablesEdit() {
        binding.edit.setCompoundDrawablesRelativeWithIntrinsicBounds(
            resources.getDrawable(R.drawable.letter, null), null, null, null
        )
    }

    private fun checkEmail(): Boolean {
        return email.matches(Regex(REGEX_EMAIL))
    }

    private fun showError() {
        binding.cardEditEmail.strokeColor = resources.getColor(R.color.red, null)
        binding.textError.visibility = View.VISIBLE
    }

    private fun hideError() {
        binding.cardEditEmail.strokeColor = resources.getColor(R.color.average_grey, null)
        binding.textError.visibility = View.GONE
    }

    private fun changeButton() {
        if (binding.buttonContinue.isEnabled) {
            binding.buttonContinue.setTextAppearance(R.style.button_enabled_continue_style)
        } else {
            binding.buttonContinue.setTextAppearance(R.style.button_disabled_continue_style)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val REGEX_EMAIL =
            """^[A-Za-z0-9+_.-]{2,}+@[A-Za-z0-9]{2,}(.+)[A-Za-z]{2,}${'$'}"""
    }
}
