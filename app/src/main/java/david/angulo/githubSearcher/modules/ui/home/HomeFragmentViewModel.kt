package david.angulo.githubSearcher.modules.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import david.angulo.githubSearcher.R
import david.angulo.githubSearcher.application.App
import david.angulo.githubSearcher.modules.base.BaseViewModel
import david.angulo.githubSearcher.modules.ui.home.repository.HomeRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * © Class created by David Angulo
 * */

class HomeFragmentViewModel(var app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }

    private val homeRepository: HomeRepository? = HomeRepository.getInstance()
    private val mDisposable = CompositeDisposable()
    val mHomeState: MutableLiveData<HomeState> = MutableLiveData()


    fun getAllPublicRepositories(count: Int) {
        homeRepository?.let {
            it.getAllPublicRepositories(count)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = { response ->
                        if (response != null && response.isNotEmpty()) {
                            mHomeState.postValue(HomeState.RepositoriesLoadedState(response))
                        } else {
                            mHomeState.postValue(HomeState.RepositoriesLoadFailedState("error"))
                        }

                    },
                    onError = { exception ->
                        exception.message?.let {
                            serverError(it)
                        }
                    })
        }?.let {
            mDisposable.add(
                it
            )
        }
    }

    fun searchRepositories(text: String, page: Int) {
        homeRepository?.let {
            homeRepository.searchRepositories(text, page)
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = { response ->
                        if (response?.items != null && response.items!!.isNotEmpty()) {
                            mHomeState.postValue(HomeState.RepositoriesLoadedState(response.items!!))
                        } else {
                            mHomeState.postValue(HomeState.EmptyRepositoriesState)
                        }
                    },
                    onError = { exception ->
                        exception.message?.let {
                            serverError(it)
                        }
                    })
        }?.let {
            mDisposable.add(
                it
            )
        }

    }

    private fun serverError(message: String){
        mHomeState.postValue(
            HomeState.RepositoriesLoadFailedState(
                app.getString(
                    R.string.server_error
                )
            )
        )
        Log.i("http_error",message)
    }
}
