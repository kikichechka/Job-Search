package com.example.jobsearch.input

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.jobsearch.input.databinding.FragmentCheckingAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckingAccountFragment : Fragment() {
    private var email: String? = null
    private var _binding: FragmentCheckingAccountBinding? = null
    private val binding: FragmentCheckingAccountBinding
        get() = _binding!!
    private var code: MutableList<Int?> = MutableList(COUNT_CODE) {
        null
    }
    private lateinit var listContainerCode: List<CodeItemView>

    interface CallbackChecking {
        fun clickButtonConfirm()
    }

    var callbackChecking: CallbackChecking? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextSentCode()
        addContainersCode()
        buttonConfirmClickListener()
    }

    @SuppressLint("SetTextI18n")
    private fun setTextSentCode() {
        binding.textSentCode.text = "${binding.textSentCode.text} $email"
    }

    private fun addContainersCode() {
        listContainerCode = List(COUNT_CODE) {
            CodeItemView(requireContext())
        }
        fillListCodeView()
        listContainerCode.first().let { editText ->
            editText.binding.editCodeOne.requestFocus()
            setCursor(editText, 0)
        }
        this.showWindowKeyboard()
    }

    private fun fillListCodeView() {
        for (item in listContainerCode) {
            binding.containerForEnteredCode.addView(item)
        }
    }

    private fun setCursor(codeItemView: CodeItemView, index: Int) {
        codeItemView.binding.editCodeOne.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!str.isNullOrEmpty()) {
                    code[index] = str.toString().toInt()
                    codeItemView.binding.editCodeOne.requestFocus(0)
                    val next = listContainerCode.getOrNull(index+1)
                    next?.let {
                        it.binding.editCodeOne.requestFocus(1)
                        setCursor(it, index+1)
                    }
                } else {
                    code[index] = null
                }
                checkEnabledButtonConfirm()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun checkEnabledButtonConfirm() {
        with(binding.buttonConfirm) {
            this.isEnabled = checkingEnteredCode()
            if (this.isEnabled) {
                this.setTextAppearance(R.style.button_enabled_continue_style)
            } else {
                this.setTextAppearance(R.style.button_disabled_continue_style)
            }
        }
    }

    private fun buttonConfirmClickListener() {
        binding.buttonConfirm.setOnClickListener {
            callbackChecking?.clickButtonConfirm()
        }
    }

    private fun checkingEnteredCode() : Boolean {
        return !code.contains(null)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val KEY_ARG = "KEY_ARG_CHECKING"
        private const val COUNT_CODE = 4
    }
}

fun Fragment.showWindowKeyboard() = WindowCompat
    .getInsetsController(requireActivity().window, requireView())
    .show(WindowInsetsCompat.Type.ime())
