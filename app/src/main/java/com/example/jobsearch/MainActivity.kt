package com.example.jobsearch

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.jobsearch.databinding.ActivityMainBinding
import com.example.jobsearch.input.CheckingAccountFragment
import com.example.jobsearch.input.LogAccountFragment
import com.example.jobsearch.input.OnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LogAccountFragment.Callback {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController

        val logAccountFragment = LogAccountFragment()
        logAccountFragment.callback = this
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, logAccountFragment)
            addToBackStack(BACK_STACK_LOG)
        }

//        val bottomNavigationView = binding.bottomNavigation
//        setupWithNavController(bottomNavigationView, navController)

        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.favouritesFragment)
        badge.isVisible = true
        badge.number = 1
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun click(str: String) {
        supportFragmentManager.commit {
            val checkingAccountFragment = CheckingAccountFragment()
            val bundle = Bundle().apply {
                putString(CheckingAccountFragment.KEY_ARG, str)
            }
            replace(R.id.nav_host_fragment, CheckingAccountFragment::class.java, bundle)
        }
    }

    companion object {
        private const val BACK_STACK_LOG = "BACK_STACK_LOG"
    }
}