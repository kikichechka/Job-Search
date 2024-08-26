package com.example.jobsearch.search.presentation.fragment.compliance

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.jobsearch.search.R
import com.example.jobsearch.search.databinding.FragmentVacanciesComplianceBinding
import com.example.jobsearch.search.presentation.adapter.VacanciesComplianceAdapter
import com.example.jobsearch.search.presentation.ClickNotFavouriteVacancy
import com.example.jobsearch.search.presentation.uimodel.ListVacancies
import com.example.jobsearch.search.presentation.uimodel.Vacancy

class VacanciesComplianceFragment : Fragment() {
    private var list: ListVacancies? = null
    private var _binding: FragmentVacanciesComplianceBinding? = null
    private val binding: FragmentVacanciesComplianceBinding
        get() = _binding!!
    private lateinit var adapter: VacanciesComplianceAdapter
//    private lateinit var clickFavouriteVacancy: ClickFavouriteVacancy
    private lateinit var clickNotFavouriteVacancy: ClickNotFavouriteVacancy

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
//            clickFavouriteVacancy = context as ClickFavouriteVacancy
            clickNotFavouriteVacancy = context as ClickNotFavouriteVacancy
        } catch (e: ClassCastException) {
            throw ClassCastException(e.message)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            list = it.getParcelable(KEY_PARAM, ListVacancies::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacanciesComplianceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter =
            VacanciesComplianceAdapter(clickVacancy = { vacancy -> clickVacancyCallback(vacancy) },
                clickFavourite = { vacancy -> clickFavorite(vacancy) },
                clickNotFavourite = { vacancy -> clickNotFavorite(vacancy) })
        list?.let {
            binding.recyclerAllVacancies.adapter = adapter
            adapter.changeData(it.listVacancies)

            val countVacancies =
                resources.getQuantityString(
                    R.plurals.count_vacancies,
                    it.listVacancies.size,
                    it.listVacancies.size
                )
            binding.numberVacancies.text = countVacancies
        }
    }

    private fun clickFavorite(vacancy: Vacancy) {
//        clickFavouriteVacancy.clickFavourite()
//        list?.let {
//            it.listVacancies[position].isFavorite = true
//            adapter.changeData(it.listVacancies)
//        }
    }

    private fun clickNotFavorite(vacancy: Vacancy) {
//        clickNotFavouriteVacancy.clickNotFavourite()
//        list?.let {
//            it.listVacancies[position].isFavorite = false
//            adapter.changeData(it.listVacancies)
//        }
    }

    private fun clickVacancyCallback(vacancy: Vacancy) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val KEY_PARAM = "COMPLIANCE_KEY_PARAM"
    }
}
