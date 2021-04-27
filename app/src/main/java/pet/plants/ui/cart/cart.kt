package pet.plants.ui.cart

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pet.plants.R
import pet.plants.ui.payment.payment
import pet.plants.ui.shop.ShopAdapter
import pet.plants.ui.shop.ShopItemData
import pet.plants.ui.shop.ShoppingData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [cart.newInstance] factory method to
 * create an instance of this fragment.
 */
class cart : Fragment() {


    private lateinit var RcView : RecyclerView
    private lateinit var adapter : cartAdapter

    private lateinit var navController: NavController
    var shoppingCart : ArrayList<ShoppingData>? = null
    var total : Double= 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)?:return
        val dataFromMemory = sharedPref.getString("cartContents", null)
        val sType = object : TypeToken<ArrayList<ShoppingData>>(){}.type
        shoppingCart = Gson().fromJson<ArrayList<ShoppingData>>(dataFromMemory, sType)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        RcView = view.findViewById(R.id.productView)
        RcView.layoutManager = LinearLayoutManager(context)
        adapter = cartAdapter(shoppingCart)
        RcView.adapter = adapter
        val totalText : TextView = view.findViewById(R.id.total_text)

        if (shoppingCart != null){
            shoppingCart?.forEach {
                total = total + (it.amount.toDouble() * it.thing.price!!.toDouble())
            }
            totalText.text = total.toString()
        }
        else
            totalText.text = "Buy Some Friends Today! :D"



        val payment = view.findViewById<Button>(R.id.payment_button)
        payment.setOnClickListener{paymentClicked()}
        return view

    }

    fun paymentClicked(){
        val sharedPrefadd = activity?.getPreferences(Context.MODE_PRIVATE)?: return
        with(sharedPrefadd.edit()){
            putString("cartContents", null)
            apply()
        }
        findNavController().navigate(R.id.navigation_payment)
    }
}