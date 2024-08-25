package com.example.jobsearch.search.presentation

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

        val recommendationsAdapter = RecommendationsAdapter(click = {uri ->  clickOfferCallback(uri)})
        binding.recyclerRecommendations.adapter = recommendationsAdapter


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.offers.collect{
                recommendationsAdapter.changeData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.vacancy.collect{

                val countVacancies = resources.getQuantityString(R.plurals.count_vacancies, it.size, it.size)
                val vacancyAdapter = VacancyAdapter(countVacancies = countVacancies, clickVacancy = {vacancy -> clickVacancyCallback(vacancy) })

                binding.recyclerVacanciesForYou.adapter = vacancyAdapter
                if (it.isNotEmpty()) {
                    vacancyAdapter.changeData(it.subList(0,3))
                }
            }
        }
    }

    private fun clickOfferCallback(uri: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(browserIntent)
    }

    private fun clickVacancyCallback(vacancy: Vacancy) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
