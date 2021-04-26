package pet.plants.ui.login

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pet.plants.MainActivity
import pet.plants.R
import android.annotation.SuppressLint
import android.widget.TextView
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs


class LoggedIn : Fragment() {
    private lateinit var email1: TextView
    private lateinit var logoutButton: Button
    private lateinit var pts_button: Button
    val args : LoggedInArgs by navArgs()
    var mContext : Context? = null


    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_logged_in, container, false)
        email1 = view.findViewById(R.id.email)
        logoutButton = view.findViewById(R.id.logout)
        pts_button = view.findViewById(R.id.go_to_pts)

        logoutButton.setOnClickListener{
            signOut(view)
            findNavController().navigate(R.id.navigation_Login)
        }
        pts_button.setOnClickListener{
            findNavController().navigate(R.id.navigation_pts)
        }

        if (args.UserEmail.isNotEmpty()) {
            email1.text = args.UserEmail
        }
        else if (args.UserEmail == "testuser@smth.com"){
            signOut(view)
            findNavController().navigate(R.id.navigation_Login)
        }



        return view
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun signOut(view:View){
        FirebaseAuth.getInstance().signOut()
        email1.text = ""
    }






    companion object {
        fun newInstance(): LoggedIn {
            return LoggedIn()
        }
    }
}