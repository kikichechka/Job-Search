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
import com.example.jobsearch.search.presentation.FavouriteVacancy
import com.example.jobsearch.search.presentation.uimodel.ListVacancies
import com.example.jobsearch.search.presentation.uimodel.Vacancy
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
    private lateinit var favouriteVacancy: FavouriteVacancy
    private lateinit var vacancyAdapter: SearchVacancyAdapter
    private var allListVacancies = listOf<Vacancy>()
    private var countVacancies: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            clickAllVacancies = context as ClickAllVacancies
            favouriteVacancy = context as FavouriteVacancy
        } catch (e: ClassCastException) {
            throw ClassCastException(e.message)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadData()
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recommendationsAdapter =
            RecommendationsAdapter(click = { uri -> clickOfferCallback(uri) })
        binding.recyclerRecommendations.adapter = recommendationsAdapter


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.offers.collect {
                recommendationsAdapter.changeData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteVacancy.collect {
                favouriteVacancy.countFavourite(it)
            }
        }

        vacancyAdapter = SearchVacancyAdapter(
            countVacancies = "${resources.getString(R.string.still)} $countVacancies",
            clickVacancy = { vacancy -> clickVacancyCallback(vacancy) },
            clickButtonAllVacancies = { clickAllVacancies() },
            clickFavourite = { vacancy -> clickFavorite(vacancy) },
            clickNotFavourite = { vacancy -> clickNotFavorite(vacancy) }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.vacancy.collect {
                allListVacancies = it
                countVacancies = resources.getQuantityString(R.plurals.count_vacancies, it.size, it.size)
                if (it.isNotEmpty()) {
                    val partList = it.subList(0, 3)
                    vacancyAdapter.changeData(partList)
                }

                binding.recyclerVacanciesForYou.adapter = vacancyAdapter
            }
        }
    }

    private fun clickAllVacancies() {
        clickAllVacancies.click(ListVacancies(allListVacancies))
    }

    private fun clickOfferCallback(uri: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(browserIntent)
    }

    private fun clickVacancyCallback(vacancy: Vacancy) {


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
}
