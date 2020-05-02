package anand.example.mvvmsample.database.daos

import anand.example.mvvmsample.model.FactsResponse
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FactsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacts(facts: FactsResponse?): Long

    @Query("SELECT * FROM facts_table")
    suspend fun getAllFacts(): FactsResponse?

    @Query("DELETE FROM facts_table")
    suspend fun deleteAllFacts()
}