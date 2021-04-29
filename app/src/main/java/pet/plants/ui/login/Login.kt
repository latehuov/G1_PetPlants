package pet.plants.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pet.plants.MainActivity
import pet.plants.R


class Login : Fragment() {

    private lateinit var uname: EditText
    private lateinit var pass: EditText
    private lateinit var RegisterButton: Button
    private lateinit var LogInButton: Button
    private lateinit var auth: FirebaseAuth
    private var currentUser: FirebaseUser? = null
     var mContext : Context? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.fragment_login, container, false)
        uname = view.findViewById(R.id.email)
        pass = view.findViewById(R.id.password)
        RegisterButton = view.findViewById(R.id.register_button)
        LogInButton = view.findViewById(R.id.login_button)


        RegisterButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_Register)

        }
        LogInButton.setOnClickListener {
            login()
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


        auth = Firebase.auth


        if(auth.currentUser != null){
            findNavController().navigate(R.id.navigation_LoggedIn)
        }

    }

    fun login(){
        var email = uname.text.toString()
        var password = pass.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            currentUser = auth.currentUser
                            val UserEmail = auth.currentUser.email
                            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)?: return@addOnCompleteListener
                            with(sharedPref.edit()){
                                putString("savedEmail", UserEmail)
                                apply()
                            }
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            
                            Toast.makeText(
                                mContext, "Oopsie!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
        }
        else{
            Toast.makeText(
                mContext, "email or password is empty!",
                Toast.LENGTH_SHORT
            ).show()
        }


    }


    companion object {
        fun newInstance(): Login {
            return Login()
        }
    }
}