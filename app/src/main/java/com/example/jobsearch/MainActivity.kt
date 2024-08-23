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
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CallbackLog,
    CallbackChecking {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
//    private val logAccountFragment = LogAccountFragment()
//    private val checkingAccountFragment = CheckingAccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
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
        //избранное
        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.favouritesFragment)
        badge.isVisible = true
        badge.number = 1
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
