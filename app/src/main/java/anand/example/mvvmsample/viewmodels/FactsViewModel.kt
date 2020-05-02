package anand.example.mvvmsample.viewmodels

import anand.example.mvvmsample.model.FactsResponse
import anand.example.mvvmsample.repository.FactsRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FactsViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var factsResponse: MutableLiveData<FactsResponse>
    private var dataLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        fetchFacts()
    }

    fun fetchFacts() {
        dataLoading.value = true
        factsResponse = FactsRepository(getApplication()).getFacts(dataLoading)
    }

    fun getTitleWithFacts(): LiveData<FactsResponse> {
        return factsResponse
    }

    fun getDataLoading(): LiveData<Boolean> {
        return dataLoading
    }
}