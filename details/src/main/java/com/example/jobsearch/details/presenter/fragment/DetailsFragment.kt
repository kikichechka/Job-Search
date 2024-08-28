package com.example.jobsearch.details.presenter.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.jobsearch.details.R
import com.example.jobsearch.details.databinding.FragmentDetailsBinding
import com.example.jobsearch.details.presenter.DetailsViewModelsFactory
import com.example.jobsearch.details.presenter.model.UiModel
import com.example.jobsearch.details.presenter.viewmodel.DetailsViewModel
import com.example.model.Vacancy
import com.example.model.callback.ClickFavouriteVacancy
import com.example.model.callback.ClickFoundInSearch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!
    private var vacancy: Vacancy? = null
    private lateinit var clickFavouriteVacancy: ClickFavouriteVacancy
    private lateinit var clickFoundInSearch: ClickFoundInSearch

    @Inject
    lateinit var detailsViewModelsFactory: DetailsViewModelsFactory
    private val viewModel: DetailsViewModel by viewModels { detailsViewModelsFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            clickFavouriteVacancy = context as ClickFavouriteVacancy
            clickFoundInSearch = context as ClickFoundInSearch
        } catch (e: ClassCastException) {
            throw ClassCastException(e.message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            vacancy = if (Build.VERSION.SDK_INT >= 33) it.getParcelable(
                KEY_ARG,
                Vacancy::class.java
            ) else it.getParcelable(KEY_ARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showVacancy()
    }

    private fun showVacancy() {
        vacancy?.let {
            buttonBackClick()
            iconIsFavouriteClick()
            installIconIsFavourite()
            titleVacancy()
            titleSalary()
            titleCompany()
            address()
            lookingNumber()
            appliedNumber()
            description()
            responsibilities()
            addQuestions(it)
            buttonRespondClick(it)
            schedules()
        }
    }

    private fun buttonRespondClick(vacancy: Vacancy) {
        binding.buttonRespond.setOnClickListener {
            val responseDialog = ResponseDialogFragment()
            val bundle = Bundle()
            val uiModel = UiModel(vacancy.title, null)
            bundle.putParcelable(
                ResponseDialogFragment.ARG_PARAM_TASK,
                uiModel
            )
            responseDialog.arguments = bundle
            responseDialog.show(childFragmentManager, ResponseDialogFragment.TAG)
        }
    }

    private fun schedules() {
        val strSchedules = vacancy?.schedules?.joinToString(separator = SEPARATOR)
        val strSchedulesUpperCase = strSchedules?.replaceFirstChar { it.uppercaseChar() }
        binding.schedules.text = strSchedulesUpperCase
    }

    private fun addQuestions(vacancy: Vacancy) {
        for (str in vacancy.questions) {
            val itemQuestion = ItemQuestionsCustomView(requireContext())
            itemQuestion.binding.textQuestions.text = str
            itemQuestion.setOnClickListener {
                val responseDialog = ResponseDialogFragment()
                val bundle = Bundle()
                val uiModel = UiModel(vacancy.title, str)
                bundle.putParcelable(
                    ResponseDialogFragment.ARG_PARAM_TASK,
                    uiModel
                )
                responseDialog.arguments = bundle
                responseDialog.show(childFragmentManager, ResponseDialogFragment.TAG)
            }
            binding.linearLayoutForItemQuestion.addView(itemQuestion)
        }
    }

    private fun description() {
        vacancy?.description?.let {
            binding.description.visibility = View.VISIBLE
            binding.description.text = it
        }
    }

    private fun responsibilities() {
        binding.responsibilities.text = vacancy?.responsibilities
    }

    private fun appliedNumber() {
        vacancy?.appliedNumber?.let {
            val appliedNumberPeoples = resources.getQuantityString(
                R.plurals.applied_number_peoples, it, it
            )
            binding.containerAppliedNumber.visibility = View.VISIBLE
            binding.appliedNumber.text = appliedNumberPeoples
        }
    }

    private fun lookingNumber() {
        vacancy?.lookingNumber?.let {
            val lookingNumberPeoples = resources.getQuantityString(
                R.plurals.looking_number_peoples, it, it
            )
            binding.containerLookingNumber.visibility = View.VISIBLE
            binding.lookingNumber.text = lookingNumberPeoples
        }
    }

    @SuppressLint("SetTextI18n")
    private fun address() {
        binding.address.text =
            "${vacancy?.address?.town}, ${vacancy?.address?.street}, ${vacancy?.address?.house}"
    }

    private fun titleCompany() {
        binding.titleCompany.text = vacancy?.company
    }

    private fun titleVacancy() {
        binding.titleVacancy.text = vacancy?.title
    }

    private fun titleSalary() {
        binding.salaryFull.text = vacancy?.salary?.full
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun installIconIsFavourite() {
        vacancy?.let {
            if (it.isFavorite)
                setFavouriteDrawable()
            else
                setNotFavouriteDrawable()
        }
    }

    private fun buttonBackClick() {
        binding.buttonBack.setOnClickListener {
            clickFoundInSearch.clickFound()
        }
    }

    private fun iconIsFavouriteClick() {
        binding.iconIsFavourite.setOnClickListener {
            vacancy?.let {
                when (it.isFavorite) {
                    true -> deleteFavourite()

                    false -> addFavourite()
                }
            }
        }
    }

    private fun addFavourite() {
        viewLifecycleOwner.lifecycleScope.launch {
            vacancy?.let {
                it.isFavorite = true
                setFavouriteDrawable()
                clickFavouriteVacancy.countFavourite(viewModel.addVacancy(it))
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setFavouriteDrawable() {
        binding.iconIsFavourite.setImageDrawable(
            resources.getDrawable(
                R.drawable.favorite_heart,
                null
            )
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setNotFavouriteDrawable() {
        binding.iconIsFavourite.setImageDrawable(
            resources.getDrawable(
                R.drawable.not_favorite_heart,
                null
            )
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun deleteFavourite() {
        viewLifecycleOwner.lifecycleScope.launch {
            vacancy?.let {
                it.isFavorite = false
                setNotFavouriteDrawable()
                clickFavouriteVacancy.countFavourite(viewModel.deleteVacancy(it))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val KEY_ARG = "KEY_ARG_VACANCY"
        private const val SEPARATOR = ", "
    }
}
