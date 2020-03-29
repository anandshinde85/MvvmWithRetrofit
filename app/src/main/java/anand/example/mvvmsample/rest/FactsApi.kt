package anand.example.mvvmsample.rest

import anand.example.mvvmsample.model.FactsResponse
import retrofit2.Call
import retrofit2.http.GET

interface FactsApi {
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getFacts(): Call<FactsResponse>
}