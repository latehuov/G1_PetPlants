package pet.plants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.navigation.ui.setupActionBarWithNavController



import android.widget.Button
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth


class   MainActivity : AppCompatActivity() {
private lateinit var uname: EditText
private lateinit var pass: EditText
private lateinit var auth: FirebaseAuth
private var currentUser: FirebaseUser? = null
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lasses navigation bullshittery
        val navView: BottomNavigationView = findViewById(R.id.navigationView)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_Shop))   //add id of your nav fragment in mobile_navigation.xml here
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //no touchy
        /*
        auth = Firebase.auth
        uname = findViewById(R.id.email)
        pass = findViewById(R.id.password)



        val RegisterButton:Button = findViewById(R.id.register_button)
        RegisterButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        val LogIn:Button = findViewById(R.id.login_button)
        LogIn.setOnClickListener {
            login()
        }*/
    }



    fun login(){
       var email = uname.text.toString()
        var password = pass.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            currentUser = auth.currentUser
                            val intent = Intent(this, LoggedInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(baseContext, "Oopsie!",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        }
        else{
            Toast.makeText(baseContext, "email or password is empty!",
                    Toast.LENGTH_SHORT).show()
        }


    }
}