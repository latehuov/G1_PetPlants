package pet.plants.ui.payment

import android.R
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView


/**
 * Created by Jaison on 27/05/17.
 */
class CreditCardFormattingTextWatcher : TextWatcher {
    private var etCard: EditText
    private var tvCard: TextView? = null
    private var ivType: ImageView? = null
    private var isDelete = false
    var creditCardType: CreditCardType? = null

    constructor(etcard: EditText, tvcard: TextView?) {
        etCard = etcard
        tvCard = tvcard
    }

    constructor(etcard: EditText, tvcard: TextView?, creditCardType: CreditCardType?) {
        etCard = etcard
        tvCard = tvcard
        this.creditCardType = creditCardType
    }

    constructor(
        etcard: EditText,
        tvcard: TextView?,
        ivType: ImageView?,
        creditCardType: CreditCardType?
    ) {
        etCard = etcard
        tvCard = tvcard
        this.ivType = ivType
        this.creditCardType = creditCardType
    }

    constructor(etcard: EditText) {
        etCard = etcard
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        isDelete = if (before == 0) false else true
    }

    override fun afterTextChanged(s: Editable) {
        val source = s.toString()
        val length = source.length
        val stringBuilder = StringBuilder()
        stringBuilder.append(source)
        if (length > 0 && length % 5 == 0) {
            if (isDelete) stringBuilder.deleteCharAt(length - 1) else stringBuilder.insert(
                length - 1,
                " "
            )
            etCard.setText(stringBuilder)
            etCard.setSelection(etCard.text.length)
        }
        if (length >= 4 && creditCardType != null) creditCardType!!.setCardType(
            CreditCardUtils.getCardType(
                source.trim { it <= ' ' })
        )
        if (tvCard != null) {
            if (length == 0) tvCard!!.text = "XXXX XXXX XXXX XXXX" else tvCard!!.text =
                stringBuilder
        }
        if (ivType != null && length == 0) ivType!!.setImageResource(R.color.transparent)
    }

    interface CreditCardType {
        fun setCardType(type: Int)
    }
}