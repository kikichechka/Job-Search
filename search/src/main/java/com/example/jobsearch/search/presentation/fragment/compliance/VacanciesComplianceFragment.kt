package com.example.jobsearch.search.presentation.fragment.compliance

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.jobsearch.search.R
import com.example.jobsearch.search.databinding.FragmentVacanciesComplianceBinding
import com.example.jobsearch.search.presentation.adapter.VacanciesComplianceAdapter
import com.example.jobsearch.search.presentation.FavouriteVacancy
import com.example.jobsearch.search.presentation.SearchViewModelsFactory
import com.example.jobsearch.search.presentation.fragment.search.ClickAllVacancies
import com.example.jobsearch.search.presentation.uimodel.Vacancy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class VacanciesComplianceFragment : Fragment() {
    private var _binding: FragmentVacanciesComplianceBinding? = null
    private val binding: FragmentVacanciesComplianceBinding
        get() = _binding!!
    private lateinit var adapter: VacanciesComplianceAdapter

    @Inject
    lateinit var searchViewModelsFactory: SearchViewModelsFactory
    private val viewModel: VacanciesComplianceViewModel by viewModels { searchViewModelsFactory }
    private lateinit var clickAllVacancies: ClickAllVacancies
    private lateinit var favouriteVacancy: FavouriteVacancy
    private lateinit var foundInSearchFragment: FoundInSearchFragment

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            clickAllVacancies = context as ClickAllVacancies
            favouriteVacancy = context as FavouriteVacancy
            foundInSearchFragment = context as FoundInSearchFragment
        } catch (e: ClassCastException) {
            throw ClassCastException(e.message)
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

        createVacanciesComplianceAdapter()
        subscribeAllVacancy()
        buttonClickListener()
        subscribeCountFavouriteVacancy()
    }

    private fun buttonClickListener() {
        binding.buttonBack.setOnClickListener {
            foundInSearchFragment.clickFound()
        }
    }

    private fun subscribeAllVacancy() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.vacancy.collect {
                adapter.changeData(it)
                val countVacancies =
                    resources.getQuantityString(
                        R.plurals.count_vacancies,
                        it.size,
                        it.size
                    )
                binding.numberVacancies.text = countVacancies
            }
        }
    }

    private fun createVacanciesComplianceAdapter() {
        adapter =
            VacanciesComplianceAdapter(clickVacancy = { vacancy -> clickVacancyCallback(vacancy) },
                clickFavourite = { vacancy -> clickFavorite(vacancy) },
                clickNotFavourite = { vacancy -> clickNotFavorite(vacancy) })
        binding.recyclerAllVacancies.adapter = adapter
    }

    private fun subscribeCountFavouriteVacancy() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteVacancy.collect {
                favouriteVacancy.countFavourite(it)
            }
        }
    }

    private fun clickFavorite(vacancy: Vacancy) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.addInFavoritesVacancy(vacancy)
        }
    }

    private fun clickNotFavorite(vacancy: Vacancy) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.removeFromFavoritesVacancy(vacancy)
        }
    }

    private fun clickVacancyCallback(vacancy: Vacancy) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
