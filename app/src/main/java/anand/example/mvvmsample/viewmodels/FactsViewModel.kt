package anand.example.mvvmsample.viewmodels

import anand.example.mvvmsample.model.FactsResponse
import anand.example.mvvmsample.repository.FactsRepository
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//class FactsViewModel(val application: Application): ViewModel() {
//    private var titleWithFacts: MutableLiveData<List<TitleWithFacts>> = MutableLiveData()
//
//    fun fetchFacts(){
//        viewModelScope.launch {
//            titleWithFacts = FactsRepository(application = application).getFacts() as MutableLiveData<List<TitleWithFacts>>
//        }
//    }
//    fun getTitleWithFacts(): LiveData<List<TitleWithFacts>>{
//        return titleWithFacts
//    }
//}

class FactsViewModel() : ViewModel() {
    private var factsResponse: MutableLiveData<FactsResponse> = MutableLiveData()

    fun fetchFacts() {
//        viewModelScope.launch {
        factsResponse.value = FactsRepository().getFacts()
//        }
    }

    fun getTitleWithFacts(): LiveData<FactsResponse> {
        return factsResponse
    }
}