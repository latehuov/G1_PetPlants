package pet.plants.ui.shop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import pet.plants.R


/**
 * A fragment representing a list of Items.
 */
class shopItemFragment : Fragment() {
    private lateinit var shopItems : ArrayList<ShopItemData>
    private lateinit var database : DatabaseReference
    private lateinit var RcView : RecyclerView
    private lateinit var  rcviewAdapter: ShopAdapter
    private lateinit var itemsListener : ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.database.reference
        shopItems = arrayListOf()
        val dbListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stuffFromDb = snapshot.value as Map<String, MutableMap<String, String>>
                shopItems.clear()
                stuffFromDb.forEach{
                    var item= ShopItemData.from(it.value)
                    shopItems.add(item)
                }
                RcView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("Chat", error.toString())
            }
        }
        database.child("products").addValueEventListener(dbListener)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_shop_list, container, false)

        RcView = view.findViewById(R.id.rcview)
        RcView.layoutManager = LinearLayoutManager(context)
        RcView.adapter = ShopAdapter(shopItems)


        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            shopItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}