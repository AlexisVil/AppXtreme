package mx.com.evotae.appxtreme.main.service.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Services(
    var name : String,
    val id: Int,
    val photo: String
) : Parcelable
