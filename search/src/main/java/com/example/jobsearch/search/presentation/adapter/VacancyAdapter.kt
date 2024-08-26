package com.example.jobsearch.search.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearch.search.R
import com.example.jobsearch.search.databinding.ItemVacancyBinding
import com.example.jobsearch.search.presentation.uimodel.Vacancy

open class VacancyAdapter(
    private val clickFavourite: (position: Int) -> Unit,
    private val clickNotFavourite: (position: Int) -> Unit
) : RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {
    var list: List<Vacancy> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    open fun changeData(newList: List<Vacancy>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        return VacancyViewHolder(
            ItemVacancyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = list[position]
        lookingNumber(holder, vacancy)
        installFavourite(holder, vacancy)
        salaryShort(holder, vacancy)
        with(holder.binding) {
            titleVacancy.text = vacancy.title
            addressTown.text = vacancy.address.town
            company.text = vacancy.company
            experiencePreviewText.text = vacancy.experience.previewText
        }
        changeFavourite(holder.binding, vacancy, position)
        publishedDate(holder.binding, vacancy)
    }

    @SuppressLint("SetTextI18n")
    private fun publishedDate(binding: ItemVacancyBinding, vacancy: Vacancy) {
        val date = vacancy.publishedDate.split("-")
        val data = date[2].toInt()
        val month = date[1].toInt() - 1
        binding.publishedDate.text =
            "${binding.root.resources.getString(R.string.published)} $data ${
                binding.root.resources.getStringArray(
                    R.array.months
                )[month]
            }"
    }

    private fun salaryShort(holder: VacancyViewHolder, vacancy: Vacancy) {

        vacancy.salary.short?.let {
            with(holder.binding) {
                salaryShort.text = it
                salaryShort.visibility = View.VISIBLE
            }
        }
        if (vacancy.salary.short == null) {
            holder.binding.salaryShort.visibility = View.GONE
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun installFavourite(holder: VacancyViewHolder, vacancy: Vacancy) {
        with(holder.binding) {
            when (vacancy.isFavorite) {
                true -> favourite(this)

                false -> notFavourite(this)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun notFavourite(binding: ItemVacancyBinding) {
        binding.isFavorite.setImageDrawable(
            binding.root.resources.getDrawable(
                R.drawable.not_favorite_heart,
                null
            )
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun favourite(binding: ItemVacancyBinding) {
        binding.isFavorite.setImageDrawable(
            binding.root.resources.getDrawable(
                R.drawable.favorite_heart,
                null
            )
        )
    }

    private fun changeFavourite(binding: ItemVacancyBinding, vacancy: Vacancy, position: Int) {
        binding.isFavorite.setOnClickListener {
            when (vacancy.isFavorite) {
                true -> {
                    notFavourite(binding)
                    clickNotFavourite(position)
                }

                false -> {
                    favourite(binding)
                    clickFavourite(position)
                }
            }
        }
    }

    private fun lookingNumber(holder: VacancyViewHolder, vacancy: Vacancy) {
        with(holder.binding) {
            currentlyPersonsViewing.visibility = View.GONE
            vacancy.lookingNumber?.let {
                val lookingNumberPeoples = root.resources.getQuantityString(
                    R.plurals.looking_number_peoples,
                    vacancy.lookingNumber,
                    vacancy.lookingNumber
                )
                currentlyPersonsViewing.visibility = View.VISIBLE
                currentlyPersonsViewing.text = lookingNumberPeoples
            }
        }
    }

    class VacancyViewHolder(val binding: ItemVacancyBinding) :
        RecyclerView.ViewHolder(binding.root)
}
