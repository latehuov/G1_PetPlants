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


class Register : Fragment() {
    private lateinit var uname: EditText
    private lateinit var pass: EditText
    private lateinit var pass2: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var RegisterButton: Button
    private lateinit var LogInButton: Button
    private var currentUser: FirebaseUser? = null
    var mContext : Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        auth = Firebase.auth
        uname = view.findViewById(R.id.email)
        pass = view.findViewById(R.id.password)
        pass2 = view.findViewById(R.id.repeat_password)
        RegisterButton = view.findViewById(R.id.register_button)
        LogInButton = view.findViewById(R.id.login_button)


        RegisterButton.setOnClickListener {
            Register()
        }
        LogInButton.setOnClickListener {
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


        auth = Firebase.auth



    }

    fun Register(){
        var email = uname.text.toString()
        var password = pass.text.toString()
        var password2 = pass2.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {
            if (password == password2) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            activity?.let{
                                val intent = Intent (it, Login::class.java)
                                it.startActivity(intent)
                            }
                        } else {
                            Toast.makeText(mContext, "error" + task.exception,
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(mContext, "Passwords don't match!",
                    Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(mContext, "email or password is empty!",
                Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(): Register {
            return Register()
        }
    }
}