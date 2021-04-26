package pet.plants.ui.payment_done

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

private lateinit var card_name: EditText
private lateinit var number: EditText
private lateinit var expiry_date: EditText
private lateinit var sec_code: EditText
private lateinit var add_1: EditText
private lateinit var add_2: EditText
private lateinit var add_city: EditText
private lateinit var postal_code: EditText

class MainActivity : AppCompatActivity() {
    lateinit var details: Array<TextView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        card_name = findViewById(R.id.name_on_the_card)
        number = findViewById((R.id.card_number))
        expiry_date = findViewById(R.id.expiration_date)
        sec_code = findViewById(R.id.cvv)
        add_1 = findViewById(R.id.address_line1)
        add_2 = findViewById(R.id.address_line2)
        add_city = findViewById(R.id.city)
        postal_code = findViewById(R.id.post_code)

        details =
                arrayOf(card_name, number, expiry_date, sec_code, add_1, add_2, add_city, postal_code)
    }


    fun buttonClick(view: View) {

        var allGood: Boolean = true

        for (i in details) {
            if (i.text.isEmpty()) {
                Toast.makeText(
                        baseContext, "Field is required!",
                        Toast.LENGTH_SHORT
                ).show()
                allGood = false
                break
            }
        }
        if (allGood)
            Toast.makeText(
                    baseContext, "Order has been sent :)",
                    Toast.LENGTH_SHORT
            ).show()

        /*if (card_name.text.isEmpty())
            Toast.makeText(
                baseContext, "Field is required!",
                Toast.LENGTH_SHORT).show()

        else if (number.text.isEmpty())
            Toast.makeText(
                baseContext, "Field is required!",
                Toast.LENGTH_SHORT).show()

        else if (expiry_date.text.isEmpty())
            Toast.makeText(
                baseContext, "Field is required!",
                Toast.LENGTH_SHORT).show()

        else if (sec_code.text.isEmpty())
            Toast.makeText(
                baseContext, "Field is required!",
                Toast.LENGTH_SHORT).show()

        else if (add_1.text.isEmpty())
            Toast.makeText(
                baseContext, "Field is required!",
                Toast.LENGTH_SHORT).show()

        else if (add_2.text.isEmpty())
            Toast.makeText(
                baseContext, "Field is required!",
                Toast.LENGTH_SHORT).show()

        else if (add_city.text.isEmpty())
            Toast.makeText(
                baseContext, "Field is required!",
                Toast.LENGTH_SHORT).show()

        else if (postal_code.text.isEmpty())
            Toast.makeText(
                baseContext, "Field is required!",
                Toast.LENGTH_SHORT).show()

        else
            Toast.makeText(
                baseContext, "Order has been sent :)",
                Toast.LENGTH_SHORT).show()*/

    }

}