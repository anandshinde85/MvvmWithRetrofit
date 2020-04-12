package anand.example.mvvmsample.viewmodels

import anand.example.mvvmsample.model.FactsResponse
import anand.example.mvvmsample.repository.FactsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FactsViewModel() : ViewModel() {
    private lateinit var factsResponse: MutableLiveData<FactsResponse>
    private var dataLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        fetchFacts()
    }

    fun fetchFacts() {
//        viewModelScope.launch {
        dataLoading.value = true
        factsResponse = FactsRepository().getFacts(dataLoading)
//        }
    }

    fun getTitleWithFacts(): LiveData<FactsResponse> {
        return factsResponse
    }

    fun getDataLoading(): LiveData<Boolean> {
        return dataLoading
    }
}