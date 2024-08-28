package com.example.jobsearch.favourites.presenter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.jobsearch.favourites.R
import com.example.jobsearch.favourites.databinding.FragmentFavouritesBinding
import com.example.model.Vacancy
import com.example.model.callback.ClickFavouriteVacancy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding: FragmentFavouritesBinding
        get() = _binding!!
    private lateinit var clickFavouriteVacancy: ClickFavouriteVacancy
    private lateinit var clickVacancyFromFavourite: ClickVacancyFromFavourite
    private lateinit var favouriteVacanciesAdapter: FavouriteVacanciesAdapter
    private var numberFavouriteVacancies: Int = 0
    @Inject
    lateinit var favouriteViewModelFactory: FavouriteViewModelsFactory
    private val viewModel: FavouriteViewModel by viewModels { favouriteViewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            clickVacancyFromFavourite = context as ClickVacancyFromFavourite
            clickFavouriteVacancy = context as ClickFavouriteVacancy
        } catch (e: ClassCastException) {
            throw ClassCastException(e.message)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        createVacancyAdapter()
        showData()
    }

    private fun showData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.vacancy.collect{
                favouriteVacanciesAdapter.changeData(it)
                val countVacancies =
                    resources.getQuantityString(
                        R.plurals.count_vacancies,
                        it.size,
                        it.size
                    )
                binding.numberVacancies.text = countVacancies
                numberFavouriteVacancies = it.size
            }
        }
    }

    private fun loadData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadData()
        }
    }

    private fun createVacancyAdapter() {
        favouriteVacanciesAdapter = FavouriteVacanciesAdapter(
            clickVacancy = { vacancy -> clickVacancyFromFavourite.clickVacancyFromFavourite(vacancy)  },
            clickFavourite = { vacancy -> clickFavorite(vacancy) },
            clickNotFavourite = { vacancy -> clickNotFavorite(vacancy) }
        )
        binding.recyclerVacanciesForYou.adapter = favouriteVacanciesAdapter
    }

    private fun clickFavorite(vacancy: Vacancy) {
        vacancy.isFavorite
    }

    private fun clickNotFavorite(vacancy: Vacancy) {
        clickFavouriteVacancy.countFavourite(--numberFavouriteVacancies)
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.deleteFavouriteVacancy(vacancy)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
