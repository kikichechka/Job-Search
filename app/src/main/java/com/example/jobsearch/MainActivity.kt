package com.example.jobsearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.NavController
import com.example.jobsearch.databinding.ActivityMainBinding
import com.example.jobsearch.input.CheckingAccountFragment
import com.example.jobsearch.input.LogAccountFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LogAccountFragment.CallbackLog,
    CheckingAccountFragment.CallbackChecking {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    private lateinit var navController: NavController
    private val logAccountFragment = LogAccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logAccountFragment.callbackLog = this
        supportFragmentManager.commit {
            add(R.id.nav_host_fragment, logAccountFragment)
        }

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController


//        val bottomNavigationView = binding.bottomNavigation
//        setupWithNavController(bottomNavigationView, navController)

//        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.favouritesFragment)
//        badge.isVisible = true
//        badge.number = 1
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun clickButtonContinue(str: String) {
        val bundle = Bundle().apply {
            putString(CheckingAccountFragment.KEY_ARG, str)
        }
        val checkingAccountFragment = CheckingAccountFragment()
        checkingAccountFragment.callbackChecking = this
        supportFragmentManager.commit {
            replace(R.id.nav_host_fragment, checkingAccountFragment::class.java, bundle)
                .addToBackStack(BACK_STACK_LOG)
        }
    }

    override fun clickButtonConfirm() {
    }

    companion object {
        private const val BACK_STACK_LOG = "BACK_STACK_LOG"
    }
}