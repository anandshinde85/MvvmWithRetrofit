package anand.example.mvvmsample.database

import anand.example.mvvmsample.database.daos.FactsDao
import anand.example.mvvmsample.model.FactsResponse
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FactsResponse::class], version = 1)
abstract class FactsDatabase : RoomDatabase() {
    abstract fun factsDao(): FactsDao

    companion object {
        private val lock = Any()
        private const val DB_NAME = "Fact.db"
        @Volatile private var instance: FactsDatabase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FactsDatabase::class.java, DB_NAME
        ).build()
    }
}