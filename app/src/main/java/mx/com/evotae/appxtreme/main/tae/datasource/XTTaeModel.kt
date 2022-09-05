package mx.com.evotae.appxtreme.main.tae.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class XTTaeModel(
    var name: String,
    val idCarrier: Int,
    val photo: String
): Parcelable