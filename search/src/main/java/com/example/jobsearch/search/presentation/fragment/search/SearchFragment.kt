package com.example.jobsearch.search.presentation.fragment.search

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.jobsearch.search.R
import com.example.jobsearch.search.databinding.FragmentSearchBinding
import com.example.jobsearch.search.presentation.SearchViewModelsFactory
import com.example.jobsearch.search.presentation.adapter.RecommendationsAdapter
import com.example.jobsearch.search.presentation.adapter.SearchVacancyAdapter
import com.example.jobsearch.search.presentation.uimodel.ListVacancies
import com.example.model.Vacancy
import com.example.model.callback.ClickFavouriteVacancy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!

    @Inject
    lateinit var searchViewModelsFactory: SearchViewModelsFactory
    private val viewModel: SearchViewModel by viewModels { searchViewModelsFactory }
    private lateinit var clickAllVacancies: ClickAllVacancies
    private lateinit var clickFavouriteVacancy: ClickFavouriteVacancy
    private lateinit var clickVacancyFromSearch: ClickVacancyFromSearch
    private lateinit var vacancyAdapter: SearchVacancyAdapter
    private var allListVacancies = listOf<Vacancy>()
    private var countVacancies: String = ""
    private lateinit var recommendationsAdapter: RecommendationsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            clickVacancyFromSearch = context as ClickVacancyFromSearch
            clickAllVacancies = context as ClickAllVacancies
            clickFavouriteVacancy = context as ClickFavouriteVacancy
        } catch (e: ClassCastException) {
            throw ClassCastException(e.message)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        createRecommendationsAdapter()
        createVacancyAdapter()
        subscribeOffers()
        subscribeAllVacancy()
        subscribeCountFavouriteVacancy()
    }

    private fun loadData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadData()
        }
    }

    private fun createVacancyAdapter() {
        vacancyAdapter = SearchVacancyAdapter(
            countVacancies = countVacancies,
            clickVacancy = { vacancy -> clickVacancyFromSearch.clickVacancyFromSearch(vacancy) },
            clickButtonAllVacancies = { clickButtonAllVacancies() },
            clickFavourite = { vacancy -> clickFavorite(vacancy) },
            clickNotFavourite = { vacancy -> clickNotFavorite(vacancy) }
        )
    }

    private fun createRecommendationsAdapter() {
        recommendationsAdapter =
            RecommendationsAdapter(click = { uri -> clickOfferCallback(uri) })
        binding.recyclerRecommendations.adapter = recommendationsAdapter
    }

    private fun subscribeOffers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.offers.collect {
                recommendationsAdapter.changeData(it)
            }
        }
    }

    private fun subscribeAllVacancy() {
        binding.recyclerVacanciesForYou.adapter = vacancyAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.vacancy.collect {
                allListVacancies = it
                countVacancies =
                    resources.getQuantityString(R.plurals.count_vacancies, it.size, it.size)
                if (it.isNotEmpty()) {
                    val partList = it.subList(FROM_INDEX, TO_INDEX)
                    vacancyAdapter.changeData(partList)
                    vacancyAdapter.changeCountVacancies(countVacancies)
                }
            }
        }
    }

    private fun subscribeCountFavouriteVacancy() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteVacancy.collect {
                clickFavouriteVacancy.countFavourite(it)
            }
        }
    }

    private fun clickButtonAllVacancies() {
        clickAllVacancies.click(ListVacancies(allListVacancies))
    }

    private fun clickOfferCallback(uri: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(browserIntent)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val FROM_INDEX = 0
        private const val TO_INDEX = 3
    }
}
