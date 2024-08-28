package com.example.jobsearch.details.presenter.fragment

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobsearch.details.R
import com.example.jobsearch.details.databinding.FragmentResponseDialogBinding
import com.example.jobsearch.details.presenter.model.UiModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResponseDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentResponseDialogBinding? = null
    private val binding: FragmentResponseDialogBinding
        get() = _binding!!
    private var uiModel: UiModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResponseDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uiModel = if (Build.VERSION.SDK_INT >= 33)
                it.getParcelable(ARG_PARAM_TASK, UiModel::class.java)
            else
                it.getParcelable(ARG_PARAM_TASK)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setVisibleResponse()
        buttonRespondClick()
    }

    private fun setVisibleResponse() {
        uiModel?.let {
            binding.titleVacancy.text = uiModel?.titleVacancy
            if (it.question != null) {
                showQuestion(it)
            } else {
                showCoverLetter()
            }
        }
    }

    private fun showQuestion(uiModel: UiModel) {
        binding.addCoverLetter.visibility = View.GONE
        binding.yourCoverLetter.text = Editable.Factory.getInstance().newEditable(uiModel.question)
    }

    private fun showCoverLetter() {
        binding.yourCoverLetter.visibility = View.GONE
        binding.addCoverLetter.setOnClickListener { view ->
            view.visibility = View.GONE
            binding.yourCoverLetter.visibility = View.VISIBLE
        }
    }

    private fun buttonRespondClick() {
        binding.buttonRespond.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"
        const val ARG_PARAM_TASK = "ARG_PARAM_TASK_RESPONSE"
    }
}