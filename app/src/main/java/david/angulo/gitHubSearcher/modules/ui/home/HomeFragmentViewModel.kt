package david.angulo.gitHubSearcher.modules.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import david.angulo.gitHubSearcher.application.App
import david.angulo.gitHubSearcher.modules.base.BaseViewModel
import david.angulo.gitHubSearcher.modules.ui.home.repository.HomeRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class HomeFragmentViewModel(var app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }

    private val homeRepository: HomeRepository = HomeRepository()
    private val mDisposable = CompositeDisposable()
    val mHomeState: MutableLiveData<HomeState> = MutableLiveData()


    fun getAllPublicRepositories() {
        mDisposable.add(
            homeRepository.getAllPublicRepositories()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = { response ->
                        if (response != null && response.isNotEmpty()) {
                            mHomeState.postValue(HomeState.RepositoriesLoaded(response))
                        } else {
                            mHomeState.postValue(HomeState.RepositoriesLoadFailed("error"))
                        }

                    },
                    onError = { exception ->
                        mHomeState.postValue(HomeState.RepositoriesLoadFailed(exception.message!!))

                    })
        )
    }

    fun searchRepositories(text: String) {
        mDisposable.add(
            homeRepository.searchRepositories(text)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = { response ->
                        if (response?.items != null) {
                            mHomeState.postValue(HomeState.RepositoriesLoaded(response.items!!))
                        } else {
                            mHomeState.postValue(HomeState.RepositoriesLoadFailed("error"))
                        }

                    },
                    onError = { exception ->
                        mHomeState.postValue(HomeState.RepositoriesLoadFailed(exception.message!!))

                    })
        )
    }
}
