package david.angulo.githubSearcher.modules.ui.home.activity

import android.app.Application
import david.angulo.githubSearcher.application.App
import david.angulo.githubSearcher.modules.base.BaseViewModel

/**
 * © Class created by David Angulo
 * */

class HomeActivityViewModel (app: Application) : BaseViewModel(app) {

    init {
        (app as? App)?.component?.inject(this)
    }
}