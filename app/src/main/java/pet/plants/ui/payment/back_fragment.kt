package pet.plants.ui.payment

import android.R
import android.app.Fragment
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.droidmentor.checkoutflow.CCFragment.CCSecureCodeFragment
import com.droidmentor.checkoutflow.Utils.FontTypeChange


/**
 * A simple [Fragment] subclass.
 */
class CardBackFragment : Fragment() {
    @BindView(R.id.tv_cvv)
    var tv_cvv: TextView? = null
    var fontTypeChange: FontTypeChange? = null
    var activity: CheckOutActivity? = null
    var securecode: CCSecureCodeFragment? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle): View? {
        // Inflate the layout for this fragment
        Log.d("BF", "onCreateView: ")
        val view: View = inflater.inflate(R.layout.fragment_card_back, container, false)
        ButterKnife.bind(this, view)
        fontTypeChange = FontTypeChange(getActivity())
        tv_cvv!!.setTypeface(fontTypeChange.get_fontface(1))
        activity = getActivity() as CheckOutActivity
        securecode = activity!!.secureCodeFragment
        securecode.setCvv(tv_cvv)
        if (!TextUtils.isEmpty(securecode.getValue())) tv_cvv.setText(securecode.getValue())
        return view
    }
}