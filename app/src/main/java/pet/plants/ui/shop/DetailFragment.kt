package pet.plants.ui.shop


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        return view
    }


    companion object {
    }
}