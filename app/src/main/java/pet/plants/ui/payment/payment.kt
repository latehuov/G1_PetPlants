package pet.plants.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.telecom.Call
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import pet.plants.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [payment.newInstance] factory method to
 * create an instance of this fragment.
 */
class payment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var card_name: EditText
    private lateinit var number: EditText
    private lateinit var expiry_date: EditText
    private lateinit var sec_code: EditText
    private lateinit var add_1: EditText
    private lateinit var add_2: EditText
    private lateinit var add_city: EditText
    private lateinit var postal_code: EditText
    lateinit var details: Array<TextView>
    lateinit var button_id: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_payment, container, false)


        card_name = view.findViewById(R.id.name_on_the_card)

        number = view.findViewById((R.id.card_number))

        expiry_date = view.findViewById(R.id.expiration_date)

        sec_code = view.findViewById(R.id.cvv)

        add_1 = view.findViewById(R.id.address_line1)

        add_2 = view.findViewById(R.id.address_line2)

        add_city = view.findViewById(R.id.city)

        postal_code = view.findViewById(R.id.post_code)

        details =
                arrayOf(
                        card_name,
                        number,
                        expiry_date,
                        sec_code,
                        add_1,
                        add_2,
                        add_city,
                        postal_code)

        button_id = view.findViewById(R.id.buy_now)

        button_id.setOnClickListener { view ->

                var allGood: Boolean = true

                for (i in details) {
                    if (i.text.isEmpty()) {
                        Toast.makeText(
                                view.context, "Field is required!",
                                Toast.LENGTH_SHORT
                        ).show()
                        allGood = false
                        break
                    }
                }
                if (allGood)
                    Toast.makeText(
                            view.context, "Order has been sent :)",
                            Toast.LENGTH_SHORT
                    ).show()
            }

        return view
    }

}