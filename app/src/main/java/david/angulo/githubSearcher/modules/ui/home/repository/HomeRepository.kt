package david.angulo.githubSearcher.modules.ui.home.repository

import david.angulo.githubSearcher.api.ApiService
import david.angulo.githubSearcher.model.GithubRepository
import david.angulo.githubSearcher.model.SearchResponse
import io.reactivex.Flowable
import javax.inject.Singleton

/**
 * © Class created by David Angulo
 * */

@Singleton
class HomeRepository {

    private val apiService = ApiService.create()

    companion object {
        private var instance: HomeRepository? = null

        fun getInstance(): HomeRepository? {
            if (instance == null) {
                synchronized(HomeRepository::class.java) {
                    if (instance == null) {
                        instance = HomeRepository()
                    }
                }
            }
            return instance
        }
    }

    fun getAllPublicRepositories(count: Int): Flowable<ArrayList<GithubRepository>> {
        return apiService.getAllPublicRepositories(count)
    }

    fun searchRepositories(text: String, page: Int): Flowable<SearchResponse> {
        return apiService.searchRepositories(text, page)
    }

}


