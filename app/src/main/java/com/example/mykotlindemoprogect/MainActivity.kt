package com.example.mykotlindemoprogect

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity(){
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(this, R.id.my_nav_host_fragment)
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            if(destination.id == R.id.blankFragment) {
                toolbar.visibility = View.GONE
//                bottomNavigationView.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
//                bottomNavigationView.visibility = View.VISIBLE
            }
        }
//        appBarConfiguration = AppBarConfiguration(navController.graph)
        return navController.navigateUp()
    }
}
