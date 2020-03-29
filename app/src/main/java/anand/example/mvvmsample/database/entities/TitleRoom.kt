package anand.example.mvvmsample.database.entities

//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import androidx.room.Relation
//
//@Entity(tableName = "title_table")
//data class TitleRoom(val title: String, @PrimaryKey(autoGenerate = true) var titleId: Int = 0)
//
//data class TitleWithFacts(
//    @Embedded val title: TitleRoom,
//
//    @Relation(
//        parentColumn = "titleId",
//        entityColumn = "titleCreatorId"
//    )
//    val factsList: List<RowRoom>
//)