package david.angulo.githubSearcher.application

import android.content.Context
import android.content.SharedPreferences
import dagger.Component
import david.angulo.githubSearcher.modules.base.BaseViewModel
import javax.inject.Singleton

/**
 * © Class created by David Angulo
 * */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun app(): App

    fun context(): Context

    fun preferences(): SharedPreferences

    fun inject(baseViewModel: BaseViewModel)



}
