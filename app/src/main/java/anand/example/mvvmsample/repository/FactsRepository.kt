package anand.example.mvvmsample.repository

import anand.example.mvvmsample.database.FactsDatabase
import anand.example.mvvmsample.database.daos.FactsDao
import anand.example.mvvmsample.helpers.SharedPreferenceHelper
import anand.example.mvvmsample.model.FactsResponse
import anand.example.mvvmsample.rest.FactsApi
import anand.example.mvvmsample.rest.RestAdapter
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NumberFormatException
import java.util.*

class FactsRepository(context: Context) {
    private val factsDao: FactsDao
    private val preferenceHelper: SharedPreferenceHelper
    private val DEFAULT_CACHE_TIME = 1000 * 60 * 60L // 1 Hour cache
    private val ONE_SECOND = 1000
    private val ONE_MINUTE = ONE_SECOND * 60

    init {
        val database = FactsDatabase(context)
        factsDao = database.factsDao()
        preferenceHelper = SharedPreferenceHelper(context)
    }

    fun getFacts(dataLoading: MutableLiveData<Boolean>): MutableLiveData<FactsResponse> {
        val titleWithFacts = MutableLiveData<FactsResponse>()
        GlobalScope.launch(Dispatchers.IO) {
            val refreshTime = getCacheDuration()
            val savedTime = preferenceHelper.getTime()
            if (savedTime != null && savedTime != 0L && Calendar.getInstance().timeInMillis - savedTime < refreshTime) {
                // Data will be loaded from cache
                factsDao.getAllFacts()?.let {
                    dataLoading.postValue(false)
                    titleWithFacts.postValue(it)
                }
            } else {
                // Clear database and fetch from remote
                factsDao.deleteAllFacts()
                fetchFromRemote(dataLoading, titleWithFacts)
            }
        }
        return titleWithFacts
    }

    private fun getCacheDuration(): Long {
        return try {
            val cacheDurationTime = preferenceHelper.getCacheDuration()
            val cacheDuration = cacheDurationTime?.toLong() ?: DEFAULT_CACHE_TIME
            cacheDuration.times(ONE_MINUTE)
        } catch (ex: NumberFormatException) {
            DEFAULT_CACHE_TIME
        }
        return DEFAULT_CACHE_TIME
    }

    private fun fetchFromRemote(
        dataLoading: MutableLiveData<Boolean>,
        titleWithFacts: MutableLiveData<FactsResponse>
    ) {
        RestAdapter.restAdapter.create(FactsApi::class.java).getFacts()
            .enqueue(object : Callback<FactsResponse> {
                override fun onFailure(call: Call<FactsResponse>, t: Throwable) {
                    dataLoading.postValue(false)
                    titleWithFacts.postValue(null)
                }

                override fun onResponse(
                    call: Call<FactsResponse>,
                    response: Response<FactsResponse>
                ) {
                    if (response.isSuccessful) {
                        dataLoading.postValue(false)
                        response.body()?.let {
                            val processedData = removeEmptyItems(it)
                            titleWithFacts.postValue(processedData)
                            GlobalScope.launch(Dispatchers.IO) {
                                factsDao.insertFacts(processedData)
                                preferenceHelper.saveTime(Calendar.getInstance().timeInMillis)
                            }
                        }
                    }
                }
            })
    }

    private fun removeEmptyItems(factsResponse: FactsResponse): FactsResponse {
        val rows = factsResponse.rows.filter { row ->
            !row.title.isNullOrBlank() && !row.description.isNullOrBlank() && !row.imageHref.isNullOrBlank()
        }
        return FactsResponse(factsResponse.title, rows)
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}