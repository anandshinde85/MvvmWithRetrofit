package anand.example.mvvmsample.database

//import anand.example.mvvmsample.database.entities.RowRoom
//import android.app.Application
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [RowRoom::class], version = 1)
//abstract class FactsDatabase : RoomDatabase() {
//    abstract fun rowDao(): RowDao
//
//    // 2
//    companion object {
//        private val lock = Any()
//        private const val DB_NAME = "Fact.db"
//        private var INSTANCE: FactsDatabase? = null
//
//        // 3
//        fun getInstance(application: Application): FactsDatabase {
//            synchronized(lock) {
//                if (INSTANCE == null) {
//                    INSTANCE =
//                        Room.databaseBuilder(
//                            application,
//                            FactsDatabase::class.java, DB_NAME
//                        )
//                            .allowMainThreadQueries()
//                            .build()
//                }
//                return INSTANCE!!
//            }
//        }
//    }
//}