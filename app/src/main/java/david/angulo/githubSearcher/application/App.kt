package david.angulo.githubSearcher.application

/**
 * © Class created by David Angulo
 * */

class App : android.app.Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}

