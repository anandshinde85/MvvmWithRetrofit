package anand.example.mvvmsample.helpers

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SharedPreferenceHelper {
    companion object {
        private const val PREF_TIME = "pref_time"
        private lateinit var preferences: SharedPreferences

        @Volatile
        private var instance: SharedPreferenceHelper? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context): SharedPreferenceHelper {
            preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferenceHelper()
        }
    }

    fun saveTime(time: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            preferences.edit()?.putLong(PREF_TIME, time)?.apply()
        }
    }

    fun getTime() = runBlocking {
        preferences.getLong(PREF_TIME, 0)
    }

    fun getCacheDuration() = runBlocking {
        preferences.getString("cache_duration", "60")
    }
}