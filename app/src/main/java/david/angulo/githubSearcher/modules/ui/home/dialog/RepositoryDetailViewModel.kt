package david.angulo.githubSearcher.modules.ui.home.dialog

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import david.angulo.githubSearcher.application.App
import david.angulo.githubSearcher.model.GithubRepository
import david.angulo.githubSearcher.modules.base.BaseViewModel
import david.angulo.githubSearcher.modules.utils.ConstantsPlatform

class RepositoryDetailViewModel(var app: Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }
    val mRepositoryDetailDialogState = MutableLiveData<RepositoryDetailState>()

    fun getArguments(arguments: Bundle?) {
        arguments?.let {
            mRepositoryDetailDialogState.postValue(
                RepositoryDetailState.DialogCreatedState(
                    arguments.getSerializable(
                        ConstantsPlatform.REPOSITORY_TAG
                    ) as GithubRepository
                )
            )
        }
    }
}