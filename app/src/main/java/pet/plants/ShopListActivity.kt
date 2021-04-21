package pet.plants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import pet.plants.ShopItemData.Companion.from

class ShopListActivity : AppCompatActivity() {
    private lateinit var shopItems : ArrayList<ShopItemData>
    private lateinit var database : DatabaseReference
    private lateinit var RcView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)
        RcView = findViewById(R.id.rcview)
        database  = Firebase.database.reference
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
        RcView.layoutManager = LinearLayoutManager(this)
        RcView.adapter = ShopAdapter(shopItems)

    }
}