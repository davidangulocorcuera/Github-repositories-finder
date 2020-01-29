package david.angulo.gitHubSearcher.modules.ui.home.activity

import android.app.Application
import david.angulo.gitHubSearcher.application.App
import david.angulo.gitHubSearcher.modules.base.BaseViewModel

class HomeActivityViewModel (app: Application) : BaseViewModel(app) {

    init {
        (app as? App)?.component?.inject(this)
    }
}