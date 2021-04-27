package pet.plants.ui.shop

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingData(val amount : Int, val thing : ShopItemData): Parcelable {
}