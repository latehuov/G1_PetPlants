package pet.plants.ui.shop

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import pet.plants.MainActivity
import pet.plants.R


/**
 * A fragment representing a list of Items.
 */
class shopItemFragment : Fragment() {
    private lateinit var shopItems : ArrayList<ShopItemData>
    private lateinit var database : DatabaseReference
    private lateinit var RcView : RecyclerView
    private lateinit var adapter : ShopAdapter
    private lateinit var mview : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        database = Firebase.database.reference
        shopItems = arrayListOf()
        val dbListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stuffFromDb = snapshot.value as Map<String, MutableMap<String, Any>>
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
        adapter = ShopAdapter(shopItems)
        RcView.adapter = adapter
        mview = view
        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)

        var searchItem = menu.findItem((R.id.app_bar_search))
        var searchView = searchItem.actionView as SearchView
        searchView.isIconifiedByDefault = false
        searchView.queryHint = "Search..."

        val magID  = resources.getIdentifier("android:id/search_mag_icon", null, null)
        val magImage : ImageView= searchView!!.findViewById(magID)
        val searchViewGroup : ViewGroup = magImage.parent as  ViewGroup
        searchViewGroup.removeView(magImage)

        searchItem.setOnActionExpandListener(
                object : MenuItem.OnActionExpandListener{
                    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                        return true
                    }

                    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                        closeKeyboard()
                        return true
                    }

                }
        )

        val queryTextListener = object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                closeKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        }
        searchView.setOnQueryTextListener(queryTextListener)
        super.onCreateOptionsMenu(menu, inflater)
    }
    fun closeKeyboard() {
        val activity = activity as MainActivity

        val view = activity.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view!!.windowToken, 0)
        }
    }
    

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        closeKeyboard()
        when(item.itemId){
            R.id.sort_price_desc -> {
                shopItems.sortedByDescending { it.price }
            }
            R.id.sort_price_asc -> {
                shopItems.sortBy { it.price }
            }
            R.id.sort_alphabet_za -> {
                shopItems.sortByDescending { it.name  }
            }
            R.id.sort_alphabet_az -> {
                shopItems.sortBy { it.name }
            }
        }

        adapter.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }

    companion object {
    }
}