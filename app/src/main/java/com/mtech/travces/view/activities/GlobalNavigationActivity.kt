package com.mtech.travces.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.mtech.travces.R
import com.mtech.travces.view.activities.base.BaseActivity
import com.mtech.travces.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_global_navigation.*

class GlobalNavigationActivity : BaseActivity() {
    lateinit var navController: NavController

    lateinit var homeViewModel: HomeViewModel

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // TODO : Clicking on notificaiton will trigger this method
        //          Use this for deep linking...
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_navigation)
        initNavigation()
    }

    private fun initNavigation() {
        ivDrawer.setOnClickListener { drawer.openDrawer(GravityCompat.START, true) }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(nav_view, navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            //            if (destination.label == getString(R.string.chat_fragment)) {
//                rlToolbar.visibility = View.GONE
//            } else {
            rlToolbar.visibility = View.VISIBLE
            tvTitle.text = destination.label
//            }
        }

        nav_view.menu.findItem(R.id.logout).setOnMenuItemClickListener {
            logout()
            true
        }
    }


    private var flagDoubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        when {
            drawer.isDrawerOpen(GravityCompat.START) -> drawer.closeDrawers()
            navController.currentDestination!!.label!!.toString() != "Home" -> super.onBackPressed()
            else -> {
                if (flagDoubleBackToExitPressedOnce) {
                    super.onBackPressed()
                    return
                }

                this.flagDoubleBackToExitPressedOnce = true
                showToast(R.string.warn_press_back_to_exit)

                Handler().postDelayed({ flagDoubleBackToExitPressedOnce = false }, 2000)
            }
        }
    }

    object Companion {
        @JvmStatic
        fun getStartIntent(context: Context) = Intent(context, GlobalNavigationActivity::class.java)
    }
}