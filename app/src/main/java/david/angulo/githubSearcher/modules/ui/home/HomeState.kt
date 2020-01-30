package david.angulo.githubSearcher.modules.ui.home

import david.angulo.githubSearcher.model.GithubRepository

sealed class HomeState{
    data class RepositoriesLoadedState(val githubRepositories: ArrayList<GithubRepository>): HomeState()
    data class RepositoriesLoadFailedState(val error: String): HomeState()
    object EmptyRepositoriesState: HomeState()

}