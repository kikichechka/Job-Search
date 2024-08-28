package com.example.jobsearch.details.presenter.fragment

import android.content.Context
import android.widget.FrameLayout
import com.example.jobsearch.details.R
import com.example.jobsearch.details.databinding.ItemQuestionsBinding

class ItemQuestionsCustomView(context: Context): FrameLayout(context) {
    val binding: ItemQuestionsBinding
    init {
        val inflatedView = inflate(context, R.layout.item_questions, this)
        binding = ItemQuestionsBinding.bind(inflatedView)
    }
}
