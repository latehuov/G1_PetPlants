package pet.plants.ui.payment

import android.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import butterknife.BindView
import butterknife.ButterKnife
import com.droidmentor.checkoutflow.Utils.CreditCardUtils.AMEX
import com.droidmentor.checkoutflow.Utils.CreditCardUtils.DISCOVER
import com.droidmentor.checkoutflow.Utils.CreditCardUtils.MASTERCARD
import com.droidmentor.checkoutflow.Utils.CreditCardUtils.NONE
import com.droidmentor.checkoutflow.Utils.CreditCardUtils.VISA
import com.droidmentor.checkoutflow.Utils.FontTypeChange


/**
 * A simple [Fragment] subclass.
 */
class CardFrontFragment : Fragment() {
    @BindView(R.id.tv_card_number)
    var number: TextView? = null

    @BindView(R.id.tv_member_name)
    var name: TextView? = null

    @BindView(R.id.tv_validity)
    var validity: TextView? = null

    @BindView(R.id.ivType)
    var cardType: ImageView? = null
    var fontTypeChange: FontTypeChange? = null
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_card_front, container, false)
        ButterKnife.bind(this, view)
        fontTypeChange = FontTypeChange(getActivity())
        number!!.setTypeface(fontTypeChange.get_fontface(3))
        name!!.setTypeface(fontTypeChange.get_fontface(3))
        return view
    }

    fun setCardType(type: Int) {
        when (type) {
            VISA -> cardType!!.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_visa))
            MASTERCARD -> cardType!!.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_mastercard))
            AMEX -> cardType!!.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_amex))
            DISCOVER -> cardType!!.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_discover))
            NONE -> cardType!!.setImageResource(R.color.transparent)
        }
    }
}