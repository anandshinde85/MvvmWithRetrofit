package anand.example.mvvmsample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FactsResponse(
    val title: String,
    val rows: List<Rows>
) : Parcelable

@Parcelize
data class Rows(
    val title: String,
    val description: String,
    val imageHref: String
) : Parcelable