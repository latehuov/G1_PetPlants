package pet.plants.ui.myplants

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import pet.plants.R
import pet.plants.ui.shop.DetailFragmentArgs


class PlantPage : Fragment() {

    val args : PlantPageArgs by navArgs()
    lateinit var plantName : TextView
    lateinit var plantSpecies : TextView
    lateinit var plantDescription : TextView
    lateinit var plantImage : ImageView
    var plantInfo : ArrayList<PlantData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_plant_page, container, false)

        plantName = view.findViewById(R.id.plantPageName)
        plantSpecies = view.findViewById(R.id.plantPageSpecies)
        plantDescription = view.findViewById(R.id.plantPageDescription)
        plantImage = view.findViewById(R.id.imageView)
        plantName.text = args.oneplant.name
        plantSpecies.text = args.oneplant.species
        plantDescription.text = args.oneplant.description
        Picasso.get().load(args.oneplant.img).into(plantImage)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlantPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PlantPage().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

}