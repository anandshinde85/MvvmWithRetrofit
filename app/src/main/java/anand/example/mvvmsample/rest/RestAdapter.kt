package anand.example.mvvmsample.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Rest adapter for creating retrofit's rest adapter
 *
 * @author Anand Shinde
 */
object RestAdapter {
    var restAdapter = createRestAdapter()
    val BASE_URL = "https://dl.dropboxusercontent.com/"

    /**
     * Method to create rest adapter with base url
     */
    private fun createRestAdapter(): Retrofit {
        // Create OkHttpBuilder
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(30, TimeUnit.SECONDS)

        // Create HTTPLogggingInterceptor
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpBuilder.addInterceptor(loggingInterceptor)

        // Create OkHttpClient
        val okHttpClient = okHttpBuilder.build()
        val builder = Retrofit.Builder()
        builder.baseUrl(BASE_URL)
        builder.client(okHttpClient)
        builder.addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }
}