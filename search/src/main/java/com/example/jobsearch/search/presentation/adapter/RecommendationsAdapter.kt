package com.example.jobsearch.search.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearch.search.databinding.ItemRecommendationBinding
import com.example.jobsearch.search.presentation.uimodel.IconRecommendationId
import com.example.jobsearch.search.presentation.uimodel.Offer

class RecommendationsAdapter(
    private val click: (uri: String) -> Unit
) : RecyclerView.Adapter<RecommendationsAdapter.RecommendationsViewHolder>() {
    private var list: List<Offer> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newList: List<Offer>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsViewHolder {
        return RecommendationsViewHolder(
            ItemRecommendationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SimpleDateFormat", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: RecommendationsViewHolder, position: Int) {
        val offer = list[position]
        holder.binding.root.setOnClickListener {
            click(offer.link)
        }

        with(holder.binding) {
            title.text = offer.title.trim()
            if (offer.button == null) {
                buttonOffers.isVisible = false
                title.maxLines = 3
            }

            when (offer.id) {
                IconRecommendationId.LEVEL_UP_RESUME.idOffer -> iconId.setImageDrawable(
                    holder.binding.root.context.getDrawable(
                        IconRecommendationId.LEVEL_UP_RESUME.icon
                    )
                )

                IconRecommendationId.NEAR_VACANCIES.idOffer -> iconId.setImageDrawable(
                    holder.binding.root.context.getDrawable(
                        IconRecommendationId.NEAR_VACANCIES.icon
                    )
                )

                IconRecommendationId.TEMPORARY_JOB.idOffer -> iconId.setImageDrawable(
                    holder.binding.root.context.getDrawable(
                        IconRecommendationId.TEMPORARY_JOB.icon
                    )
                )
                null -> iconId.isVisible = false
            }

            offer.button?.text?.let {
                buttonOffers.text = it
                title.maxLines = 2
            }
        }
    }

    class RecommendationsViewHolder(val binding: ItemRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root)
}