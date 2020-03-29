package anand.example.mvvmsample.repository

import anand.example.mvvmsample.model.FactsResponse
import anand.example.mvvmsample.rest.FactsApi
import anand.example.mvvmsample.rest.RestAdapter
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class FactsRepository(val application: Application) {
//    private val titleRowsDao: TitleRowsDao
//
//    init {
//        val database: FactsDatabase = FactsDatabase.getInstance(application)
//        titleRowsDao = database.rowDao()
//    }
//
//    fun getFacts(): LiveData<List<TitleWithFacts>> {
//        val titleWithFacts: MutableLiveData<List<TitleWithFacts>> = MutableLiveData(mutableListOf())
//        if (isNetworkAvailable(application)) {
//            RestAdapter.restAdapter.create(FactsApi::class.java).getFacts()
//                .enqueue(object : Callback<FactsResponse> {
//                    override fun onFailure(call: Call<FactsResponse>, t: Throwable) {
//                        titleWithFacts.postValue(null)
//                    }
//
//                    override fun onResponse(
//                        call: Call<FactsResponse>,
//                        response: Response<FactsResponse>
//                    ) {
//                        if (response.code() == 200) {
//                            val factsResponse = response.body()
//
//                            factsResponse?.let {
//                                // Insert title
//                                val titleRoom = TitleRoom(it.title)
//                                titleRowsDao.insertTitle(titleRoom)
//
//                                val titleId = titleRowsDao.getTitle(it.title)
//
//                                // Insert Rows
//                                val rowList: MutableList<RowRoom> = mutableListOf()
//                                it.rows.forEach { row ->
//                                    val rowRoom = RowRoom(
//                                        title = row.title,
//                                        description = row.description,
//                                        imageHref = row.imageHref,
//                                        titleCreatorId = titleId
//                                    )
//                                    rowList.add(rowRoom)
//                                }
//                                titleRowsDao.insertAllRow(rowList)
//                                titleWithFacts.postValue(titleRowsDao.getTitleWithFacts())
//                            }
//                        }
//                    }
//                })
//        } else {
//            titleWithFacts.postValue(titleRowsDao.getTitleWithFacts())
//        }
//        return titleWithFacts
//    }
//
//    fun isNetworkAvailable(context: Context?): Boolean {
//        if (context == null) return false
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            val capabilities =
//                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (capabilities != null) {
//                when {
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                        return true
//                    }
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                        return true
//                    }
//                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
//                        return true
//                    }
//                }
//            }
//        } else {
//            val activeNetworkInfo = connectivityManager.activeNetworkInfo
//            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
//                return true
//            }
//        }
//        return false
//    }
//}


class FactsRepository() {

    fun getFacts(): FactsResponse? {
        var titleWithFacts: FactsResponse? = null
        RestAdapter.restAdapter.create(FactsApi::class.java).getFacts()
            .enqueue(object : Callback<FactsResponse> {
                override fun onFailure(call: Call<FactsResponse>, t: Throwable) {
//                    titleWithFacts.postValue(null)
                }

                override fun onResponse(
                    call: Call<FactsResponse>,
                    response: Response<FactsResponse>
                ) {
                    if (response.code() == 200) {
                        titleWithFacts = response.body()!!
                    }
                }
            })
        return titleWithFacts
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