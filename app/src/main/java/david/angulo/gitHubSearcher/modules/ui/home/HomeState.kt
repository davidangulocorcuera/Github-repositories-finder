package david.angulo.gitHubSearcher.modules.ui.home

import david.angulo.gitHubSearcher.model.GitHubRepository

sealed class HomeState{
    data class RepositoriesLoadedState(val gitHubRepositories: ArrayList<GitHubRepository>): HomeState()
    data class RepositoriesLoadFailedState(val error: String): HomeState()
    object EmptyRepositoriesState: HomeState()

}