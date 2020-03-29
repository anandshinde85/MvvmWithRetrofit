package anand.example.mvvmsample.database.daos

//import anand.example.mvvmsample.database.entities.RowRoom
//import anand.example.mvvmsample.database.entities.TitleRoom
//import anand.example.mvvmsample.database.entities.TitleWithFacts
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//
//@Dao
//interface TitleRowsDao {
//    @Insert
//    fun insertRow(rowRoom: RowRoom)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAllRow(rows: List<RowRoom>)
//
//    @Query("SELECT * FROM facts_table")
//    fun getAllRows(): LiveData<List<RowRoom>>
//
//    @Query("DELETE FROM facts_table")
//    fun deleteAllRows()
//
//    @Insert
//    fun insertTitle(rowRoom: TitleRoom)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAllTitles(rows: List<TitleRoom>)
//
//    @Query("SELECT * FROM title_table")
//    fun getAllTitle(): LiveData<List<TitleRoom>>
//
//    @Query("SELECT titleId FROM title_table WHERE title = :title")
//    fun getTitle(title: String): Int
//
//    @Query("DELETE FROM title_table")
//    fun deleteAllTitles()
//
//    @Query("SELECT * FROM title_table")
//    fun getTitleWithFacts(): List<TitleWithFacts>
//}