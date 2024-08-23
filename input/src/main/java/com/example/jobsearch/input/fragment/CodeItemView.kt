package com.example.jobsearch.input.fragment

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.jobsearch.input.R
import com.example.jobsearch.input.databinding.CodeItemViewBinding

class CodeItemView(context: Context, attributeSet: AttributeSet? = null) :
    FrameLayout(context, attributeSet) {
    val binding: CodeItemViewBinding
    init {
        val inflatedView = inflate(context, R.layout.code_item_view, this)
        binding = CodeItemViewBinding.bind(inflatedView)
    }
}
