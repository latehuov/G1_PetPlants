package pet.plants.ui.payment

import android.text.TextUtils
import android.util.Log
import java.util.*


object CreditCardUtils {
    const val NONE = 0
    const val VISA = 1
    const val MASTERCARD = 2
    const val DISCOVER = 3
    const val AMEX = 4
    const val VISA_PREFIX = "4"
    const val MASTERCARD_PREFIX = "51,52,53,54,55,"
    const val DISCOVER_PREFIX = "6011"
    const val AMEX_PREFIX = "34,37,"
    fun getCardType(cardNumber: String): Int {
        if (cardNumber.substring(
                0,
                1
            ) == VISA_PREFIX
        ) return VISA else if (MASTERCARD_PREFIX.contains(
                cardNumber.substring(
                    0,
                    2
                ) + ","
            )
        ) return MASTERCARD else if (AMEX_PREFIX.contains(
                cardNumber.substring(
                    0,
                    2
                ) + ","
            )
        ) return AMEX else if (cardNumber.substring(0, 4) == DISCOVER_PREFIX) return DISCOVER
        return NONE
    }

    fun isValid(cardNumber: String): Boolean {
        if (!TextUtils.isEmpty(cardNumber) && cardNumber.length >= 4) if (getCardType(cardNumber) == VISA && (cardNumber.length == 13 || cardNumber.length == 16)) return true else if (getCardType(
                cardNumber
            ) == MASTERCARD && cardNumber.length == 16
        ) return true else if (getCardType(cardNumber) == AMEX && cardNumber.length == 15) return true else if (getCardType(
                cardNumber
            ) == DISCOVER && cardNumber.length == 16
        ) return true
        return false
    }

    fun isValidDate(cardValidity: String): Boolean {
        if (!TextUtils.isEmpty(cardValidity) && cardValidity.length == 5) {
            val month = cardValidity.substring(0, 2)
            val year = cardValidity.substring(3, 5)
            var monthpart = -1
            var yearpart = -1
            try {
                monthpart = month.toInt() - 1
                yearpart = year.toInt()
                val current = Calendar.getInstance()
                current[Calendar.DATE] = 1
                current[Calendar.HOUR] = 12
                current[Calendar.MINUTE] = 0
                current[Calendar.SECOND] = 0
                current[Calendar.MILLISECOND] = 0
                val validity = Calendar.getInstance()
                validity[Calendar.DATE] = 1
                validity[Calendar.HOUR] = 12
                validity[Calendar.MINUTE] = 0
                validity[Calendar.SECOND] = 0
                validity[Calendar.MILLISECOND] = 0
                if (monthpart > -1 && monthpart < 12 && yearpart > -1) {
                    validity[Calendar.MONTH] = monthpart
                    validity[Calendar.YEAR] = yearpart + 2000
                } else return false
                Log.d("Util", "isValidDate: " + current.compareTo(validity))
                if (current.compareTo(validity) <= 0) return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }
}