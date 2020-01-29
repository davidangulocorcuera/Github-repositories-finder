package david.angulo.gitHubSearcher.modules.ui.home

import david.angulo.gitHubSearcher.model.GitHubRepository

sealed class HomeState{
    data class RepositoriesLoaded(val gitHubRepositories: ArrayList<GitHubRepository>): HomeState()
    data class RepositoriesLoadFailed(val error: String): HomeState()
}