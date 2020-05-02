package anand.example.mvvmsample.model

import anand.example.mvvmsample.helpers.RowsStateConverter
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "facts_table")
@TypeConverters(RowsStateConverter::class)
@Parcelize
data class FactsResponse(
    @ColumnInfo
    val title: String,
    val rows: List<Rows>
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Parcelize
data class Rows(
    val title: String?,
    val description: String?,
    val imageHref: String?
) : Parcelable