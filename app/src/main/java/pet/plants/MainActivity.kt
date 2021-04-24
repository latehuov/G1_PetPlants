package pet.plants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Fragment
import android.widget.Toast
import androidx.navigation.ui.setupActionBarWithNavController


import com.google.firebase.auth.FirebaseUser

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView




class   MainActivity : AppCompatActivity() {

    //lateinit var toolbar : ActionBar

    /*          README

               to add your view (fragment) into bottomnav,

               1)create a package into ui folder
               package should contain the following:
               a)Your fragment (generates view, gets data)
               b)Your adapter (bind data to view)

               2)copy - paste fragment code in R.navigation.mobile_navigation.xml
               with your fragments data

               3) add layout files needed into r.layout

               4)add your id from mobile_navigation.xml into line 70 of this file
     */

    var UserEmail = intent?.getStringExtra("UserEmail")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //lasses navigation bullshittery
        val navView: BottomNavigationView = findViewById(R.id.navigationView)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_Login, R.id.navigation_Shop))   //add id of your nav fragment in mobile_navigation.xml here
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //no touchy

    }

    fun changeFragment(fragment:Fragment){
        getFragmentManager()
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit();
    }






}