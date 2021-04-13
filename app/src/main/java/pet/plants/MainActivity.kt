package pet.plants
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var uname: EditText
private lateinit var pass: EditText
private lateinit var auth: FirebaseAuth
private var currentUser: FirebaseUser? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        }
    }

    fun login(){
       var email = uname.text.toString()
        var password = pass.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful){
                    currentUser = auth.currentUser
                    val intent = Intent(this, LoggedInActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(baseContext, "Oopsie!",
                    Toast.LENGTH_SHORT).show()
                }
            }


    }
}