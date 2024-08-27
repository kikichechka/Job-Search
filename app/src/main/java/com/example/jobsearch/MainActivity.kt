package com.example.jobsearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.jobsearch.databinding.ActivityMainBinding
import com.example.jobsearch.input.CallbackChecking
import com.example.jobsearch.input.CallbackLog
import com.example.jobsearch.input.fragment.CheckingAccountFragment
import com.example.jobsearch.search.presentation.fragment.compliance.ClickFoundInSearch
import com.example.jobsearch.search.presentation.fragment.search.ClickAllVacancies
import com.example.jobsearch.search.presentation.uimodel.ListVacancies
import com.example.model.callback.ClickFavouriteVacancy
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CallbackLog,
    CallbackChecking, ClickAllVacancies, ClickFavouriteVacancy, ClickFoundInSearch {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var badge: BadgeDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        badge = binding.bottomNavigation.getOrCreateBadge(R.id.favouritesFragment)
        badge.isVisible = false

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView = binding.bottomNavigation
        setupWithNavController(bottomNavigationView, navController)

        bottomNavigationView.menu.forEach { it.isEnabled = false }
    }


    override fun clickButtonContinue(str: String) {
        val bundle = Bundle().apply {
            putString(CheckingAccountFragment.KEY_ARG, str)
        }
        navController.navigate(R.id.action_logAccountFragment_to_checkingAccountFragment, bundle)
    }

    override fun clickButtonConfirm() {
        navController.navigate(R.id.action_checkingAccountFragment_to_searchFragment)
        bottomNavigationView.menu.forEach { it.isEnabled = true }
    }

    override fun click(listVacancies: ListVacancies) {
        navController.navigate(R.id.action_searchFragment_to_vacanciesComplianceFragment)
    }

    override fun countFavourite(number: Int) {
        if (badge.number != number) {
            badge.number = number
            checkBadge()
        }
    }

    private fun checkBadge() {
        if (badge.number > 0 && !badge.isVisible) {
            badge.isVisible = true
        }
        if (badge.number == 0 && badge.isVisible) {
            badge.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun clickFound() {
        navController.navigate(R.id.action_vacanciesComplianceFragment_to_searchFragment)
    }
}
