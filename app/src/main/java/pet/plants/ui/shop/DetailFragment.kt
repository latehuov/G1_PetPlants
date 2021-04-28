package pet.plants.ui.shop


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.w3c.dom.Text
import pet.plants.MainActivity
import pet.plants.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    val args : DetailFragmentArgs by navArgs()
    lateinit var itemname : TextView
    lateinit var image : ImageView
    lateinit var desc : TextView
    lateinit var detailPrice : TextView
    lateinit var amount : TextView
    lateinit var minus :ImageView
    lateinit var addimg : ImageView
    lateinit var addToCartButton : Button
    var shoppingCart : ArrayList<ShoppingData>? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)?:return
        val dataFromMemory = sharedPref.getString("cartContents", null)
        val sType = object : TypeToken<ArrayList<ShoppingData>>(){}.type
        shoppingCart = Gson().fromJson<ArrayList<ShoppingData>>(dataFromMemory, sType)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)


        itemname = view.findViewById(R.id.name)
        itemname.text = args.itemsDataFromShop.name
        image = view.findViewById(R.id.detailimage)
        image.setImageResource(R.drawable.yetanother_drawable)
        desc = view.findViewById(R.id.detailDesc)
        desc.text = args.itemsDataFromShop.description
        detailPrice = view.findViewById(R.id.descPrice)
        detailPrice.text = args.itemsDataFromShop.price

        amount= view.findViewById(R.id.amount)


        minus = view.findViewById(R.id.minus)
        minus.setOnClickListener{
            view-> var foo = amount.text.toString().toInt()
            if(foo > 1)
                amount.text = (foo-1).toString()
        }

        addimg = view.findViewById(R.id.add)
        addimg.setOnClickListener{
            view -> var foo = amount.text.toString().toInt()
            amount.text = (foo+1).toString()
        }


        addToCartButton = view.findViewById(R.id.addToCart)
        addToCartButton.setOnClickListener { addToCart() }

        return view
    }

    fun addToCart(){
        var foo = amount.text.toString().toInt()
        var arraything =ShoppingData(foo, args.itemsDataFromShop)
        if(shoppingCart == null)
                shoppingCart = arrayListOf(arraything)
        else
            shoppingCart?.add(arraything)
        var jsonObject = Gson().toJson(shoppingCart)
        val sharedPrefadd = activity?.getPreferences(Context.MODE_PRIVATE)?: return
        with(sharedPrefadd.edit()){
            putString("cartContents", jsonObject)
            apply()
        }
    }

    companion object {
    }
}