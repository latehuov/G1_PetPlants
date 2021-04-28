package pet.plants.ui.myplants

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
import pet.plants.MainActivity
import pet.plants.R
import pet.plants.ui.shop.ShopItemData
import pet.plants.ui.shop.ShoppingData
import java.io.IOException


class MyPlantsFragment : Fragment() {
    private lateinit var RcView : RecyclerView
    private lateinit var PlantButton: Button
    private lateinit var plantList : ArrayList<PlantData>
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.database.reference
        plantList = arrayListOf()
        val dbListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stuffFromDb = snapshot.value as List<HashMap<String,String>>
                plantList.clear()
               stuffFromDb.forEach{
                    var item= PlantData.from(it)
                    plantList.add(item)
                }
                RcView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("Chat", error.toString())
            }
        }
        database.child("plants").addValueEventListener(dbListener)




    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_my_plants, container, false)
        val item = inflater.inflate(R.layout.my_plant_item, container,false)


        RcView = view.findViewById(R.id.plantRecycler)
        RcView.layoutManager = LinearLayoutManager(context)
        RcView.adapter = MyPlantsAdapter(plantList)
        PlantButton = item.findViewById(R.id.plantInfoPage)

        PlantButton.setOnClickListener{
            findNavController().navigate(R.id.navigation_plantpage)
        }

        return view
    }
}