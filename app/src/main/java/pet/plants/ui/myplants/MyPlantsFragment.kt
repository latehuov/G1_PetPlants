package pet.plants.ui.myplants

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pet.plants.R
import pet.plants.ui.myplants.MyPlantsAdapter


class MyPlantsFragment : Fragment() {
    private lateinit var plants : ArrayList<PlantData>
    private lateinit var RcView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        plants = arrayListOf()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_my_plants, container, false)

        RcView = view.findViewById(R.id.plantRecycler)
        RcView.layoutManager = LinearLayoutManager(context)
        RcView.adapter = MyPlantsAdapter(plants)

        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MyPlantsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}