package com.example.jobsearch.search.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ViewUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsearch.search.R
import com.example.jobsearch.search.databinding.ItemVacancyBinding
import com.example.jobsearch.search.presentation.uimodel.Vacancy

class VacancyAdapter(
    private val countVacancies: String,
    private val clickVacancy: (vacancy: Vacancy) -> Unit
) : RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder>() {
    private var list: List<Vacancy> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newList: List<Vacancy>) {
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

    @SuppressLint("SimpleDateFormat", "UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = list[position]
        lookingNumber(holder, vacancy)
        favourite(holder, vacancy)
        salaryShort(holder, vacancy)
        with(holder.binding) {
            titleVacancy.text = vacancy.title
            addressTown.text = vacancy.address.town
            company.text = vacancy.company
            experiencePreviewText.text = vacancy.experience.previewText

            val date = vacancy.publishedDate.split("-")
            val data = date[2].toInt()
            val month = date[1].toInt() - 1
            publishedDate.text =
                "Опубликовано $data ${root.resources.getStringArray(R.array.months)[month]}"
        }

        buttonAllVacancies(holder, position)
    }

    private fun salaryShort(holder: VacancyViewHolder, vacancy: Vacancy) {
        vacancy.salary.short?.let {
            with(holder.binding) {
                salaryShort.text = it
                salaryShort.visibility = View.VISIBLE
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun favourite(holder: VacancyViewHolder, vacancy: Vacancy) {
        with(holder.binding) {
            when (vacancy.isFavorite) {
                true -> isFavorite.setImageDrawable(
                    root.resources.getDrawable(
                        R.drawable.favorite_heart,
                        null
                    )
                )

                false -> isFavorite.setImageDrawable(
                    root.resources.getDrawable(
                        R.drawable.not_favorite_heart,
                        null
                    )
                )
            }
        }
    }

    private fun buttonAllVacancies(holder: VacancyViewHolder, position: Int) {
        with(holder.binding) {
            buttonAllVacancies.visibility = View.GONE
            if (position == list.size - 1) {
                buttonAllVacancies.visibility = View.VISIBLE
                buttonAllVacancies.text = countVacancies
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
