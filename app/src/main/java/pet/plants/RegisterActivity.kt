package pet.plants
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private lateinit var uname: EditText
private lateinit var pass: EditText
private lateinit var pass2: EditText
private lateinit var auth: FirebaseAuth



class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        uname = findViewById(R.id.email)
        pass = findViewById(R.id.password)
        pass2 = findViewById(R.id.repeat_password)

        val LoginButton:Button = findViewById(R.id.login_button)
        LoginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val RegisterButton:Button = findViewById(R.id.register_button)
        RegisterButton.setOnClickListener {
            Register()
        }
    }

    fun Register(){
        var email = uname.text.toString()
        var password = pass.text.toString()
        var password2 = pass2.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {
            if (password == password2) {
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(baseContext, "error" + task.exception,
                                        Toast.LENGTH_SHORT).show()
                            }
                        }
            } else {
                Toast.makeText(baseContext, "Passwords don't match!",
                        Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(baseContext, "email or password is empty!",
                    Toast.LENGTH_SHORT).show()
        }
    }
}