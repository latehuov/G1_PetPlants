package pet.plants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController


import com.google.firebase.auth.FirebaseUser

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import pet.plants.ui.payment.payment




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

    val PREFS_NAME = "MyPrefsFile"

    lateinit var navController: NavController
    var UserEmail = intent?.getStringExtra("UserEmail")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getPreferences(Context.MODE_PRIVATE)?:return
        val email = sharedPref.getString("savedEmail",null)


        //lasses navigation bullshittery
        val navView: BottomNavigationView = findViewById(R.id.navigationView)

        navController = findNavController(R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_Login, R.id.navigation_Shop, R.id.navigation_myplants))   //add id of your nav fragment in mobile_navigation.xml here
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //no touchy

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
    fun changeFragment(fragment:Fragment){
        getFragmentManager()
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit();
    }


}