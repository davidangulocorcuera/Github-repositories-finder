package david.angulo.gitHubSearcher.api

import david.angulo.gitHubSearcher.model.GitHubRepository
import david.angulo.gitHubSearcher.model.SearchResponse
import david.angulo.gitHubSearcher.modules.utils.ConstantsPlatform
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface ApiService {

    @GET("repositories")
    fun getAllPublicRepositories(@Query("since") count: Int): Flowable<ArrayList<GitHubRepository>>

    @GET("search/repositories")
    fun searchRepositories(@Query("q") topic: String): Flowable<SearchResponse>

    companion object {

        private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(ConstantsPlatform.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}